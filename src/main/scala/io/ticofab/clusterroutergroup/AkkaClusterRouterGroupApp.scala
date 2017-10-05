package io.ticofab.clusterroutergroup

import akka.actor.{Actor, ActorSystem, Props}
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

object AkkaClusterRouterGroupApp extends App {
  implicit val as = ActorSystem("akka-cluster-router-group")
  implicit val am = ActorMaterializer()
  implicit val ec = as.dispatcher

  val roles = ConfigFactory.load().getStringList("akka.cluster.roles")

  if (roles.contains("seed")) {
    val master = as.actorOf(Props[GroupMaster])
    as.scheduler.schedule(0.seconds, 1.second, master, "hello")
  } else {
    as.actorOf(Props[Worker], "worker")
  }

}

class GroupMaster extends Actor with ClusterGroupRouting {
  println(s"creating master")
  override val ac = context

  override def receive = {
    case s: String => workerRouter ! s
  }
}

