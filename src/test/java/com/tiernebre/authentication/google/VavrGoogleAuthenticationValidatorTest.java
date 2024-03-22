package com.tiernebre.authentication.google;

import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.vavr.collection.List;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

public final class VavrGoogleAuthenticationValidatorTest {

  private final VavrGoogleAuthenticationValidator validator =
    new VavrGoogleAuthenticationValidator();

  @Test
  public void parseCredential() throws Exception {
    TestCaseRunner.run(
      VavrGoogleAuthenticationValidatorTest.class,
      List.of(
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, String>
        >(
          "Null request",
          null,
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Google Authentication Request received was null."
              )
            )
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, String>
        >(
          "No Body CSRF Token",
          new GoogleAuthenticationRequest("creds", null, "csrf"),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Google Body CSRF token is a required field."
              )
            )
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, String>
        >(
          "No Cookie CSRF Token",
          new GoogleAuthenticationRequest("creds", "csrf", null),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Google Cookie CSRF token is a required field."
              )
            )
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, String>
        >(
          "No Body and Cookie CSRF Token",
          new GoogleAuthenticationRequest("creds", null, null),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Google Cookie CSRF token is a required field. Google Body CSRF token is a required field."
              )
            )
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, String>
        >(
          "Body and Cookie CSRF Tokens are not equal",
          new GoogleAuthenticationRequest("creds", "body", "cookie"),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Google Cookie CSRF token must match Google Body CSRF token."
              )
            )
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, String>
        >(
          "No Credential",
          new GoogleAuthenticationRequest(null, "csrf", "csrf"),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Google Credential token is a required field."
              )
            )
        ),
        new TestCase<
          GoogleAuthenticationRequest,
          Either<ZoneBlitzError, String>
        >(
          "Valid request",
          new GoogleAuthenticationRequest("credential", "csrf", "csrf"),
          __ -> Either.right("credential")
        )
      ),
      input -> validator.parseCredential(input)
    );
  }
}
