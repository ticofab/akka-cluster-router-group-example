package io.ticofab.clusterroutergroup

import akka.actor.{ActorContext, Props}
import akka.cluster.routing.{ClusterRouterPool, ClusterRouterPoolSettings}
import akka.routing.RoundRobinPool

trait ClusterPoolRouting {
  val ac: ActorContext
  val workerRouter = ac.actorOf(
    ClusterRouterPool(
      RoundRobinPool(0),
      ClusterRouterPoolSettings(
        totalInstances = 100,
        maxInstancesPerNode = 1,
        allowLocalRoutees = false)
    ).props(Props[Worker]),
    name = "workerRouter")
}
