package io.ticofab.clusterroutergroup

import akka.actor.ActorContext
import akka.cluster.routing.{ClusterRouterGroup, ClusterRouterGroupSettings}
import akka.routing.RoundRobinGroup

trait ClusterGroupRouting {
  implicit val ac: ActorContext
  val workerRouter = ac.actorOf(
    ClusterRouterGroup(
      RoundRobinGroup(Nil),
      ClusterRouterGroupSettings(
        totalInstances = 100,
        routeesPaths = List("/user/worker"),
        allowLocalRoutees = false)).props(),
    name = "workerRouter")
}
