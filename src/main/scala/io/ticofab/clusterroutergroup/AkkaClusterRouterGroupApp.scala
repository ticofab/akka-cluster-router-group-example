package io.ticofab.clusterroutergroup

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import akka.actor.{Actor, ActorSystem, Props}
import akka.cluster.routing.{ClusterRouterGroup, ClusterRouterGroupSettings}
import akka.routing.RoundRobinGroup
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

object AkkaClusterRouterGroupApp extends App {
  implicit val as = ActorSystem("akka-cluster-router-group")
  implicit val am = ActorMaterializer()
  implicit val ec = as.dispatcher

  val roles = ConfigFactory.load().getStringList("akka.cluster.roles")

  if (roles.contains("seed")) {
    val master = as.actorOf(Props[Master])
    as.scheduler.schedule(0.seconds, 1.second, master, "hello")
  } else {
    as.actorOf(Props[Worker], "worker")
  }

}

class Master extends Actor {

  println(s"creating master")
  val workerRouter = context.actorOf(
    ClusterRouterGroup(
      RoundRobinGroup(Nil),
      ClusterRouterGroupSettings(
        totalInstances = 100,
        routeesPaths = List("/user/worker"),
        allowLocalRoutees = false)).props(),
    name = "workerRouter")

  override def receive = {
    case s: String => workerRouter ! s
  }
}

class Worker extends Actor {
  println(s"creating worker")
  override def receive = {
    case s: String => println(s"${LocalDateTime.now.format(DateTimeFormatter.ISO_LOCAL_TIME)}, $s")
  }
}
