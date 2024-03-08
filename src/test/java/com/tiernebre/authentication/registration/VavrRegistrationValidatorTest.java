package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertEquals;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import org.junit.Test;

public final class VavrRegistrationValidatorTest {

  private final RegistrationValidator validator =
    new VavrRegistrationValidator();

  private final record Case(
    String name,
    String username,
    String password,
    String confirmPassword,
    Either<Seq<String>, RegistrationRequest> expected
  ) {}

  @Test
  public void validate() {
    var cases = new Case[] {
      new Case(
        "null username",
        null,
        "password",
        "password",
        Either.left(List.of("Username is a required field."))
      ),
      new Case(
        "empty string username",
        "",
        "password",
        "password",
        Either.left(List.of("Username is a required field."))
      ),
      new Case(
        "blank string username",
        " ",
        "password",
        "password",
        Either.left(List.of("Username is a required field."))
      ),
      new Case(
        "null password",
        "username",
        null,
        null,
        Either.left(List.of("Password is a required field."))
      ),
      new Case(
        "empty string password",
        "username",
        "",
        "",
        Either.left(List.of("Password is a required field."))
      ),
      new Case(
        "blank string password",
        "username",
        " ",
        "password",
        Either.left(List.of("Password is a required field."))
      ),
      new Case(
        "too short of a password",
        "username",
        "a".repeat(RegistrationConstants.MINIMUM_PASSWORD_LENGTH - 1),
        "a".repeat(RegistrationConstants.MINIMUM_PASSWORD_LENGTH - 1),
        Either.left(List.of("Password must be at least 8 characters long."))
      ),
      new Case(
        "too long of a password",
        "username",
        "a".repeat(RegistrationConstants.MAXIMUM_PASSWORD_LENGTH + 1),
        "a".repeat(RegistrationConstants.MAXIMUM_PASSWORD_LENGTH + 1),
        Either.left(List.of("Password must be at most 64 characters long."))
      ),
      new Case(
        "confirm password is not equal",
        "username",
        "passwordA",
        "passwordB",
        Either.left(List.of("Confirm password must match password."))
      ),
      new Case(
        "valid happy path",
        "username",
        "password",
        "password",
        Either.right(new RegistrationRequest("username", "password"))
      ),
    };
    for (var test : cases) {
      assertEquals(
        test.expected(),
        validator.validate(
          test.username(),
          test.password(),
          test.confirmPassword()
        )
      );
    }
  }
}
