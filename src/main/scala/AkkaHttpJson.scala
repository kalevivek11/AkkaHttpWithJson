

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport


object AkkaHttpJson extends  JsonFormatter with SprayJsonSupport{

  implicit val system = ActorSystem(Behaviors.empty, "actorSystem")

  val route: Route = (path("api" / "user" ) & post) {
    entity(as[Person]) { person =>
      complete(UserAdded(UUID.randomUUID().toString, System.currentTimeMillis()))
    }
  }

  def main(args: Array[String]):Unit ={
    Http().newServerAt("localhost",8081).bind(route)
  }
}

case class Person(name: String, age: Int)
case class UserAdded(id: String, timestamp: Long)