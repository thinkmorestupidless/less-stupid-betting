
val akkaVersion = "2.5.4"

organization in ThisBuild := "less.stupid"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.2"

EclipseKeys.projectFlavor in Global := EclipseProjectFlavor.Java

lazy val `less-stupid-betting` = (project in file("."))
  .aggregate(`betfair-models`, `betfair-client`, `betfair-client-asynchttp`,`betting-services`, `spark-jobs`)

lazy val `betfair-models` = (project in file("betfair-models"))
  .settings(
    scalacOptions in Compile ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
    javacOptions in Compile ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    javacOptions in doc in Compile := Seq("-Xdoclint:none"),
    // disable parallel tests
    parallelExecution in Test := false,
    licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0"))),
    libraryDependencies ++= Seq(
      "com.google.code.gson" % "gson" % "2.6.2",
      "org.projectlombok" % "lombok" % "1.16.10",
      "joda-time" % "joda-time" % "2.9.3" % "compile",
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.10" % "test"
    )
  )

lazy val `betfair-client` = (project in file("betfair-client"))
  .settings(
    scalacOptions in Compile ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
    javacOptions in Compile ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    javacOptions in doc in Compile := Seq("-Xdoclint:none"),
    // disable parallel tests
    parallelExecution in Test := false,
    licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0"))),
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-simple" % "1.7.25",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.5",
      "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.9.2",
      "com.opengamma.strata" % "strata-collect" % "1.4.2",
      "com.google.guava" % "guava" % "23.6-jre",
      "com.typesafe" % "config" % "1.3.2",
      "com.typesafe.akka" %% "akka-stream" % "2.5.8",
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.10" % "test"
    )
  )
.dependsOn(`betfair-models`)

lazy val `betfair-client-asynchttp` = (project in file("betfair-client-asynchttp"))
  .settings(
    scalacOptions in Compile ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
    javacOptions in Compile ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    javacOptions in doc in Compile := Seq("-Xdoclint:none"),
    // disable parallel tests
    parallelExecution in Test := false,
    licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0"))),
    libraryDependencies ++= Seq(
      "org.asynchttpclient" % "async-http-client" % "2.0.37",
      "junit" % "junit" % "4.12" % "test",
      "com.novocode" % "junit-interface" % "0.10" % "test"
    )
  )
  .dependsOn(`betfair-client`)

lazy val `betting-services` = (project in file("services"))
  .settings(
    scalacOptions in Compile ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
    javacOptions in Compile ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),
    javacOptions in doc in Compile := Seq("-Xdoclint:none"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.55",
      "com.typesafe.akka" %% "akka-http" % "10.0.10",
      "com.datastax.cassandra" % "cassandra-driver-extras" % "3.1.4",
      "org.apache.kafka" % "kafka-clients" % "0.10.2.0",
      "org.pcollections" % "pcollections" % "2.1.2",
      "com.github.javafaker" % "javafaker" % "0.10",
      "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % "0.55" % Test,
      "org.scalatest" %% "scalatest" % "3.0.1" % Test),
    fork in run := true,
    mainClass in (Compile, run) := Some("less.stupid.betting.exchange.ExchangeApp"),
    // disable parallel tests
    parallelExecution in Test := false,
    licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))
  )
.dependsOn(`betfair-client-asynchttp`)

lazy val `spark-jobs` = (project in file("spark"))
.settings(
  libraryDependencies ++= Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.2.0",
    "org.apache.spark" % "spark-sql_2.11" % "2.2.0"
  )
)
