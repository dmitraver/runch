package com.github.dmitraver.runchbot

import org.scalatest.{FunSuite, Matchers}

class TokenVerifierSuite extends FunSuite with Matchers {

  test("Set token as a property and verify afterwards") {
    System.setProperty("COMMAND_VERIFICATION_TOKEN", "test_token")

    TokenVerifier.verifyToken("test_token") shouldBe true
  }

}
