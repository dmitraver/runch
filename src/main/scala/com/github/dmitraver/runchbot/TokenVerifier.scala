package com.github.dmitraver.runchbot

import com.typesafe.config.ConfigFactory

object TokenVerifier {

  private val CommandTokenKey = "slack.commands.token"

  private val config = ConfigFactory.load()

  def verifyToken(token: String): Boolean = {
    token == config.getString(CommandTokenKey)
  }
}
