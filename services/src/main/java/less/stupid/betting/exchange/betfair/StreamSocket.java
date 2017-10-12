package less.stupid.betting.exchange.betfair;

import akka.Done;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;

public class StreamSocket {

    private static final Logger log = LoggerFactory.getLogger(StreamSocket.class);

    private static final String CRLF = "\r\n";

    private final AtomicLong counter = new AtomicLong();

    private final ObjectMapper mapper = new ObjectMapper();

    private final List<Object> requests = Lists.newCopyOnWriteArrayList();

    private final Config conf;

    private Socket socket;

    private BufferedReader reader;

    private BufferedWriter writer;

    private Thread readThread;

    public StreamSocket(Config conf) {
        this.conf = conf;
    }

    public CompletionStage<StreamEnvelope> send(Object message) {
        CompletableFuture future = new CompletableFuture();

        messagesById.put(counter.incrementAndGet(), message);

        return future;
    }

    public void doSend(Object message) {
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
    }

    public void connect(Config conf) {

        if (socket == null) {
            socket = connectSocket(createSocket(conf));
        }
    }

    public CompletionStage<Done> disconnect() {
        return CompletableFuture.completedFuture(Done.getInstance());
    }

    public Socket connectSocket(Socket socket) {
        try {
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
        } catch (SocketException e) {
            throw new RuntimeException("failed to set property on socket", e);
        } catch (IOException e) {
            throw new RuntimeException("failed to get stream from socket", e);
        }

        return socket;
    }

    public Socket createSocket(Config conf) {
        String hostName = conf.getString("betfair.stream.uri");
        int port = 80;

        if (hostName.contains(":")) {
            String[] parts = hostName.split(":");
            hostName = parts[0];
            port = Integer.parseInt(parts[1]);
        }

        if (port == 443) {
            try {
                SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(hostName, port);
                socket.startHandshake();

                return socket;
            } catch (IOException e) {
                throw new RuntimeException("failed to create SSL Socket", e);
            }
        } else {
            try {
                return new Socket(hostName, port);
            } catch (IOException e) {
                throw new RuntimeException("failed to create Socket", e);
            }
        }
    }
}
