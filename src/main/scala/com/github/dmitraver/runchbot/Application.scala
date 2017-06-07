package com.github.dmitraver.runchbot

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.pattern.ask
import akka.util.Timeout
import com.github.dmitraver.runchbot.LunchLotteryActor.{GetPartner, Partner}

import scala.concurrent.duration._

object Application extends HttpApp {

  private implicit val timeout = Timeout(5 seconds)

  private val system = ActorSystem("Runch")
  private val lunchLotteryActor = system.actorOf(Props[LunchLotteryActor], "runchActor")

  override def route: Route =
    path("api" / "commands") {
      post {
        formFields('token, 'team_id, 'quser_id, 'user_name, 'command, 'text, 'response_url) { (token, teamId, userId, userName, command, text, responseUrl) =>
          if (!TokenVerifier.verifyToken(token)) complete(StatusCodes.Forbidden, "Token verification has failed.")
          else {
            val reply = lunchLotteryActor ? GetPartner(User(userId, userName, teamId, text))
            onSuccess(reply) { result =>
              result.asInstanceOf[Partner].partner.fold(complete("Hi there! I've added you to the list of participants. I will notify you once I have a match so stay tuned!")) { partner =>
                complete(s"It's me again. @${partner.userName} was picked to have a lunch with you!")
              }
            }
          }
        }
      }
    }
}

object App {
  def main(args: Array[String]): Unit = {
    Application.startServer("localhost", 8080)
  }
}

