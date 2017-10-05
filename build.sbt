
name := "akka-cluster-router-group-example"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= {
  val akkaVersion = "2.5.6"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion
  )
}
