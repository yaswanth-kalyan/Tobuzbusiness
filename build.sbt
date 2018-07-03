name := """tobuz"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  ws,
"org.postgresql" % "postgresql" % "9.4.1211",
  	"javax.persistence" % "persistence-api" % "1.0",
   	"org.json" % "json" % "20090211",
   	"org.mongodb" % "mongo-java-driver" % "3.0.2",
   	"javax.mail" % "javax.mail-api" % "1.5.1",
   	"com.sun.mail" % "smtp" % "1.4.5",
   	 "com.amazonaws" % "aws-java-sdk-s3" % "1.10.60"

)

