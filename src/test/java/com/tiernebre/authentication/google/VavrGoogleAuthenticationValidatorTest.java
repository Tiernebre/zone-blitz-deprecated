package com.tiernebre.authentication.google;

import static org.junit.Assert.assertEquals;

import io.vavr.control.Either;
import org.junit.Test;

public final class VavrGoogleAuthenticationValidatorTest {

  private final VavrGoogleAuthenticationValidator validator =
    new VavrGoogleAuthenticationValidator();

  private final record Case(
    String name,
    GoogleAuthenticationRequest request,
    Either<String, String> expected
  ) {}

  @Test
  public void cases() throws Exception {
    var cases = new Case[] {
      new Case(
        "Null request",
        null,
        Either.left("Google Authentication Request received was null.")
      ),
      new Case(
        "No Body CSRF Token",
        new GoogleAuthenticationRequest("creds", null, "csrf"),
        Either.left("Google CSRF token received was an empty string.")
      ),
      new Case(
        "No Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", "csrf", null),
        Either.left("Google CSRF token received was an empty string.")
      ),
      new Case(
        "No Body and Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", null, null),
        Either.left("Google CSRF token received was an empty string.")
      ),
      new Case(
        "Body and Cookie CSRF Tokens are not equal",
        new GoogleAuthenticationRequest("creds", "body", "cookie"),
        Either.left("Google CSRF tokens do not match each other.")
      ),
      new Case(
        "No Credential",
        new GoogleAuthenticationRequest(null, "csrf", "csrf"),
        Either.left("Google Credential received was an empty string.")
      ),
      new Case(
        "Valid request",
        new GoogleAuthenticationRequest("credential", "csrf", "csrf"),
        Either.right("credential")
      ),
    };
    for (var test : cases) {
      assertEquals(test.expected(), validator.parseCredential(test.request()));
    }
  }
}
