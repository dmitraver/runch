package com.github.dmitraver.runchbot

import java.time.{LocalTime, ZoneId}


object RegistrationChecker {

  /*
   * Defines a deadline for a random lunch registration.
   */
  private val RegistrationDeadline = new LocalTime(11, 30, 0, 0)

  /**
    * Checks if the lunch registration is still opened.
    * For now this method is implemented in a very hacky way, it ignores the actual timezone of the user.
    * @return true if the registration is opened, false otherwise.
    */
  def isRegistrationOpened(): Boolean = {

    // TODO: fix this dirty hack that ignores the timezone of the user
    val localTime = LocalTime.now(ZoneId.of("Europe/Berlin"))
    localTime.isAfter(RegistrationDeadline)
  }
}
