package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import org.junit.Test;

public final class VavrRegistrationValidatorTest {

  private final RegistrationValidator validator =
    new VavrRegistrationValidator();

  @Test
  public void parseCreate() {
    TestCaseRunner.run(
      VavrRegistrationValidatorTest.class,
      List.of(
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "null request",
          null,
          __ -> Either.left(List.of("Create registration request is null."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "null username",
          new CreateRegistrationRequest(null, "password", "password"),
          __ -> Either.left(List.of("Username is a required field."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "empty string username",
          new CreateRegistrationRequest("", "password", "password"),
          __ -> Either.left(List.of("Username is a required field."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "blank string username",
          new CreateRegistrationRequest(" ", "password", "password"),
          __ -> Either.left(List.of("Username is a required field."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "too long of a username",
          new CreateRegistrationRequest(
            "a".repeat(AuthenticationConstants.USERNAME_MAXIMUM_LENGTH + 1),
            "password",
            "password"
          ),
          __ ->
            Either.left(
              List.of(
                String.format(
                  "Username cannot be greater than 64 characters long.",
                  AuthenticationConstants.USERNAME_MAXIMUM_LENGTH
                )
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "null password",
          new CreateRegistrationRequest("username", null, "password"),
          __ -> Either.left(List.of("Password is a required field."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "empty string password",
          new CreateRegistrationRequest("username", "", "password"),
          __ -> Either.left(List.of("Password is a required field."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "blank string password",
          new CreateRegistrationRequest("username", " ", "password"),
          __ -> Either.left(List.of("Password is a required field."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "too short of a password",
          new CreateRegistrationRequest(
            "username",
            "a".repeat(AuthenticationConstants.PASSWORD_MINIMUM_LENGTH - 1),
            "a".repeat(AuthenticationConstants.PASSWORD_MINIMUM_LENGTH - 1)
          ),
          __ ->
            Either.left(
              List.of("Password cannot be lesser than 8 characters long.")
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "too long of a password",
          new CreateRegistrationRequest(
            "username",
            "a".repeat(AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH + 1),
            "a".repeat(AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH + 1)
          ),
          __ ->
            Either.left(
              List.of("Password cannot be greater than 64 characters long.")
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "confirm password is not equal",
          new CreateRegistrationRequest("username", "passwordA", "passwordB"),
          __ -> Either.left(List.of("Confirm password must match password."))
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, RegistrationRequest>
        >(
          "valid happy path",
          new CreateRegistrationRequest("username", "password", "password"),
          __ -> Either.right(new RegistrationRequest("username", "password"))
        )
      ),
      request -> validator.parse(request)
    );
  }
}
