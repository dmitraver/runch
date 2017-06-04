package com.github.dmitraver.runchbot

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{HttpApp, Route}

object Application extends HttpApp {

  override protected def route: Route =
    path("api" / "commands") {
      post {
        formFields('token, 'team_id, 'user_id, 'user_name, 'command, 'text, 'response_url) { (token, teamId, userId, userName, command, text, responseUrl) =>
          if (!TokenVerifier.verifyToken(token)) complete(StatusCodes.Forbidden, "Token verification has failed.")
          else complete("Got it!")
        }
      }
    }
}

object App {
  def main(args: Array[String]): Unit = {
    Application.startServer("localhost", 8080)
  }
}

