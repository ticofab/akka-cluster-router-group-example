package io.ticofab.clusterroutergroup

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import akka.actor.Actor

class Worker extends Actor {
  println(s"creating worker")

  override def receive = {
    case s: String => println(s"${self.path.name}, ${LocalDateTime.now.format(DateTimeFormatter.ISO_LOCAL_TIME)}, $s")
  }
}