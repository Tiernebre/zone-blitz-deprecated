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
    CreateRegistrationRequest request,
    Either<Seq<String>, RegistrationRequest> expected
  ) {}

  @Test
  public void parseCreate() {
    var cases = new Case[] {
      new Case(
        "null request",
        null,
        Either.left(List.of("Create registration request is null."))
      ),
      new Case(
        "null username",
        new CreateRegistrationRequest(null, "password", "password"),
        Either.left(List.of("Username is a required field."))
      ),
      new Case(
        "empty string username",
        new CreateRegistrationRequest("", "password", "password"),
        Either.left(List.of("Username is a required field."))
      ),
      new Case(
        "blank string username",
        new CreateRegistrationRequest(" ", "password", "password"),
        Either.left(List.of("Username is a required field."))
      ),
      new Case(
        "null password",
        new CreateRegistrationRequest("username", null, "password"),
        Either.left(List.of("Password is a required field."))
      ),
      new Case(
        "empty string password",
        new CreateRegistrationRequest("username", "", "password"),
        Either.left(List.of("Password is a required field."))
      ),
      new Case(
        "blank string password",
        new CreateRegistrationRequest("username", " ", "password"),
        Either.left(List.of("Password is a required field."))
      ),
      new Case(
        "too short of a password",
        new CreateRegistrationRequest(
          "username",
          "a".repeat(RegistrationConstants.MINIMUM_PASSWORD_LENGTH - 1),
          "a".repeat(RegistrationConstants.MINIMUM_PASSWORD_LENGTH - 1)
        ),
        Either.left(List.of("Password must be at least 8 characters long."))
      ),
      new Case(
        "too long of a password",
        new CreateRegistrationRequest(
          "username",
          "a".repeat(RegistrationConstants.MAXIMUM_PASSWORD_LENGTH + 1),
          "a".repeat(RegistrationConstants.MAXIMUM_PASSWORD_LENGTH + 1)
        ),
        Either.left(List.of("Password must be at most 64 characters long."))
      ),
      new Case(
        "confirm password is not equal",
        new CreateRegistrationRequest("username", "passwordA", "passwordB"),
        Either.left(List.of("Confirm password must match password."))
      ),
      new Case(
        "valid happy path",
        new CreateRegistrationRequest("username", "password", "password"),
        Either.right(new RegistrationRequest("username", "password"))
      ),
    };
    for (var test : cases) {
      assertEquals(test.expected(), validator.parse(test.request()));
    }
  }
}
