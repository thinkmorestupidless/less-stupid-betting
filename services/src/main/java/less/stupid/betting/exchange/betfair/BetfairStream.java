package less.stupid.betting.exchange.betfair;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.Set;
import java.util.function.Consumer;

public class BetfairStream {

    private static final Logger log = LoggerFactory.getLogger(BetfairStream.class);

    private static final String CRLF = "\r\n";

    private final ObjectMapper mapper = new ObjectMapper();

    private final Config config;

    private final SessionProvider sessions;

    private Set<Consumer<Object>> listeners = Sets.newHashSet();

    private Socket socket;

    private BufferedReader reader;

    private BufferedWriter writer;

    private Thread readThread;

    public BetfairStream(SessionProvider sessions, Config config) {
        this.sessions = sessions;
        this.config = config;
    }

    public BetfairStream addListener(Consumer<Object> listener) {
        listeners.add(listener);
        return this;
    }

    public void authenticate() {
        sessions.session().thenAccept(session -> {
           try {
               connectSocket();

               BetfairStreamMessages.AuthenticationMessage message = new BetfairStreamMessages.AuthenticationMessage(1, "authentication", session.applicationKey(), session.sessionToken());

               try {
                   String line = mapper.writeValueAsString(message);
                   log.info("writing -> {}", line);

                   writer.write(line);
                   writer.write(CRLF);
                   writer.flush();
               } catch (JsonProcessingException e) {
                   log.error("error processing JSON -> {}", e);
               } catch (Exception e) {
                   log.error("error writing line -> {}", e);
               }
           } catch (IOException e) {
               throw new RuntimeException("failed to create socket", e);
           }
        }).toCompletableFuture().join();
    }

    public void disconnect() throws IOException {
        if (socket != null) {
            socket.close();
        }

        if (reader != null) {
            reader.close();
        }

        if (writer != null) {
            writer.close();
        }
    }

    public void connectSocket() throws IOException {
        socket = createSocket();
        socket.setReceiveBufferSize(1024 * 100 * 2);
        socket.setSoTimeout(30 * 1000);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        (readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String line = reader.readLine();

                        log.info(line);
                    } catch (Exception e) {
                        log.error("problem reading from socket -> {}", e);
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        log.error("interruption -> {}", e);
                    }
                }
            }
        }))
        .start();

        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public Socket createSocket() throws IOException {
        String hostName = config.getString("betfair.stream.uri");
        int port = 80;

        if (hostName.contains(":")) {
            String[] parts = hostName.split(":");
            hostName = parts[0];
            port = Integer.parseInt(parts[1]);
        }

        if (port == 443) {
            SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(hostName, port);
            socket.startHandshake();

            return socket;
        } else {
            return new Socket(hostName, port);
        }
    }
}
