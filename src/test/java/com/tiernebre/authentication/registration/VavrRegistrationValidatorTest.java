package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.AuthenticationConstants;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzValidationError;
import io.vavr.collection.List;
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
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "null request",
          null,
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Create registration request is null.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "null username",
          new CreateRegistrationRequest(null, "password", "password"),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Username is a required field.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "empty string username",
          new CreateRegistrationRequest("", "password", "password"),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Username is a required field.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "blank string username",
          new CreateRegistrationRequest(" ", "password", "password"),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Username is a required field.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "too long of a username",
          new CreateRegistrationRequest(
            "a".repeat(AuthenticationConstants.USERNAME_MAXIMUM_LENGTH + 1),
            "password",
            "password"
          ),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of(
                  String.format(
                    "Username cannot be greater than 64 characters long.",
                    AuthenticationConstants.USERNAME_MAXIMUM_LENGTH
                  )
                )
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "null password",
          new CreateRegistrationRequest("username", null, "password"),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Password is a required field.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "empty string password",
          new CreateRegistrationRequest("username", "", "password"),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Password is a required field.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "blank string password",
          new CreateRegistrationRequest("username", " ", "password"),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Password is a required field.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "too short of a password",
          new CreateRegistrationRequest(
            "username",
            "a".repeat(AuthenticationConstants.PASSWORD_MINIMUM_LENGTH - 1),
            "a".repeat(AuthenticationConstants.PASSWORD_MINIMUM_LENGTH - 1)
          ),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Password cannot be lesser than 8 characters long.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "too long of a password",
          new CreateRegistrationRequest(
            "username",
            "a".repeat(AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH + 1),
            "a".repeat(AuthenticationConstants.PASSWORD_MAXIMUM_LENGTH + 1)
          ),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Password cannot be greater than 64 characters long.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
        >(
          "confirm password is not equal",
          new CreateRegistrationRequest("username", "passwordA", "passwordB"),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                List.of("Confirm password must match password.")
              )
            )
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, RegistrationRequest>
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
