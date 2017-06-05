package com.github.dmitraver.runchbot.db

import com.github.dmitraver.runchbot.model.User
import slick.jdbc.PostgresProfile.api._

object TablesNames {
  val Users = "users"
}

class UsersTable(tag: Tag) extends Table[User](tag, TablesNames.Users) {

  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def userId: Rep[String] = column[String]("user_id")
  def teamId: Rep[String] = column[String]("team_id")
  def city: Rep[String] = column[String] ("city")

  def * = (id, userId, teamId, city) <> (User.tupled, User.unapply)
}