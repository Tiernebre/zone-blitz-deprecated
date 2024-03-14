package com.tiernebre.authentication.google;

import static org.junit.Assert.assertEquals;

import com.tiernebre.util.error.ZoneBlitzServerError;
import io.vavr.control.Either;
import org.junit.Test;

public final class VavrGoogleAuthenticationValidatorTest {

  private final VavrGoogleAuthenticationValidator validator =
    new VavrGoogleAuthenticationValidator();

  private final record Case(
    String name,
    GoogleAuthenticationRequest request,
    Either<ZoneBlitzServerError, String> expected
  ) {}

  @Test
  public void cases() throws Exception {
    var cases = new Case[] {
      new Case(
        "Null request",
        null,
        Either.left(
          new ZoneBlitzServerError(
            "Google Authentication Request received was null."
          )
        )
      ),
      new Case(
        "No Body CSRF Token",
        new GoogleAuthenticationRequest("creds", null, "csrf"),
        Either.left(
          new ZoneBlitzServerError(
            "Google Body CSRF token is a required field."
          )
        )
      ),
      new Case(
        "No Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", "csrf", null),
        Either.left(
          new ZoneBlitzServerError(
            "Google Cookie CSRF token is a required field."
          )
        )
      ),
      new Case(
        "No Body and Cookie CSRF Token",
        new GoogleAuthenticationRequest("creds", null, null),
        Either.left(
          new ZoneBlitzServerError(
            "Google Cookie CSRF token is a required field. Google Body CSRF token is a required field."
          )
        )
      ),
      new Case(
        "Body and Cookie CSRF Tokens are not equal",
        new GoogleAuthenticationRequest("creds", "body", "cookie"),
        Either.left(
          new ZoneBlitzServerError(
            "Google Cookie CSRF token must match Google Body CSRF token."
          )
        )
      ),
      new Case(
        "No Credential",
        new GoogleAuthenticationRequest(null, "csrf", "csrf"),
        Either.left(
          new ZoneBlitzServerError(
            "Google Credential token is a required field."
          )
        )
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
