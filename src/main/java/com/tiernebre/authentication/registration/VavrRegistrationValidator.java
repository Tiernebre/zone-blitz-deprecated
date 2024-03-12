package com.tiernebre.authentication.registration;

import static com.tiernebre.authentication.AuthenticationConstants.*;
import static com.tiernebre.util.validation.VavrValidationUtils.*;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;

public final class VavrRegistrationValidator implements RegistrationValidator {

  @Override
  public Either<Seq<String>, RegistrationRequest> parse(
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
      .toEither();
  }

  private Validation<String, String> validateUsername(String username) {
    var fieldName = "Username";
    return required(username, fieldName).flatMap(
      maximumLength(fieldName, USERNAME_MAXIMUM_LENGTH)
    );
  }

  private Validation<String, String> validatePassword(
    String password,
    String confirmPassword
  ) {
    var fieldName = "Password";
    return required(password, fieldName)
      .flatMap(maximumLength(fieldName, PASSWORD_MAXIMUM_LENGTH))
      .flatMap(minimumLength(fieldName, PASSWORD_MINIMUM_LENGTH))
      .flatMap(value -> this.passwordsMatch(value, confirmPassword));
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
  public Either<Seq<String>, RegistrationAuthenticationRequest> parse(
    RegistrationAuthenticationRequest request
  ) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }
}
