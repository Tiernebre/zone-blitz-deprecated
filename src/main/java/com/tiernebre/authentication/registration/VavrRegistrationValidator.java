package com.tiernebre.authentication.registration;

import static com.tiernebre.authentication.AuthenticationConstants.*;
import static com.tiernebre.util.validation.VavrValidationUtils.*;

import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzValidationError;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;

public final class VavrRegistrationValidator implements RegistrationValidator {

  private final String USERNAME_FIELD_NAME = "Username";
  private final String PASSWORD_FIELD_NAME = "Password";

  @Override
  public Either<ZoneBlitzError, RegistrationRequest> parse(
    CreateRegistrationRequest request
  ) {
    return Option.of(request)
      .toValidation(
        (Seq<String>) List.of("Create registration request is null.")
      )
      .flatMap(
        req ->
          Validation.combine(
            validateUsername(req.username()),
            validatePassword(req.password(), req.confirmPassword())
          ).ap(RegistrationRequest::new)
      )
      .toEither()
      .mapLeft(ZoneBlitzValidationError::new);
  }

  private Validation<String, String> validateUsername(String username) {
    return required(username, USERNAME_FIELD_NAME).flatMap(
      maximumLength(USERNAME_FIELD_NAME, USERNAME_MAXIMUM_LENGTH)
    );
  }

  private Validation<String, String> validatePassword(
    String password,
    String confirmPassword
  ) {
    return required(password, PASSWORD_FIELD_NAME)
      .flatMap(maximumLength(PASSWORD_FIELD_NAME, PASSWORD_MAXIMUM_LENGTH))
      .flatMap(minimumLength(PASSWORD_FIELD_NAME, PASSWORD_MINIMUM_LENGTH))
      .flatMap(value -> passwordsMatch(value, confirmPassword));
  }

  private Validation<String, String> passwordsMatch(
    String password,
    String confirmPassword
  ) {
    return password.equals(confirmPassword)
      ? Validation.valid(password)
      : Validation.invalid("Confirm password must match password.");
  }

  @Override
  public Either<ZoneBlitzError, RegistrationAuthenticationRequest> parse(
    RegistrationAuthenticationRequest request
  ) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }
}
