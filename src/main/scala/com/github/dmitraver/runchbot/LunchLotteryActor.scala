package com.github.dmitraver.runchbot

import akka.actor.Actor
import com.github.dmitraver.runchbot.LunchLotteryActor.{GetPartner, Partner}

class LunchLotteryActor extends Actor {

  private val map = scala.collection.mutable.HashMap.empty[(String, String), User]

  override def receive: Receive = {
    case GetPartner(user) =>
      val partner = getPartner(user)
      sender() ! Partner(partner)
  }

  def getPartner(user: User): Option[User] = {
    map.remove((user.teamId, user.city)) match {
      case matchedUser @ Some(_)  => matchedUser
      case None =>
        map += ((user.teamId, user.city) -> user)
        None
    }
  }
}

object LunchLotteryActor {
  case class GetPartner(user: User)
  case class Partner(partner: Option[User])
}
