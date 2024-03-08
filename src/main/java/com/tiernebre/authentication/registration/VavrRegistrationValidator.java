package com.tiernebre.authentication.registration;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

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
    return StringUtils.isBlank(username)
      ? Validation.invalid("Username is a required field.")
      : Validation.valid(username);
  }

  private Validation<String, String> validatePassword(
    String password,
    String confirmPassword
  ) {
    return passwordIsRequired(password)
      .flatMap(this::passwordIsLongEnough)
      .flatMap(this::passwordIsShortEnough)
      .flatMap(value -> this.passwordsMatch(value, confirmPassword));
  }

  private Validation<String, String> passwordIsRequired(String password) {
    return StringUtils.isNotBlank(password)
      ? Validation.valid(password)
      : Validation.invalid("Password is a required field.");
  }

  private Validation<String, String> passwordIsLongEnough(String password) {
    return password.length() >= RegistrationConstants.MINIMUM_PASSWORD_LENGTH
      ? Validation.valid(password)
      : Validation.invalid(
        String.format(
          "Password must be at least %s characters long.",
          RegistrationConstants.MINIMUM_PASSWORD_LENGTH
        )
      );
  }

  private Validation<String, String> passwordIsShortEnough(String password) {
    return password.length() <= RegistrationConstants.MAXIMUM_PASSWORD_LENGTH
      ? Validation.valid(password)
      : Validation.invalid(
        String.format(
          "Password must be at most %s characters long.",
          RegistrationConstants.MAXIMUM_PASSWORD_LENGTH
        )
      );
  }

  private Validation<String, String> passwordsMatch(
    String password,
    String confirmPassword
  ) {
    return password.equals(confirmPassword)
      ? Validation.valid(password)
      : Validation.invalid(
        String.format(
          "Confirm password must match password.",
          RegistrationConstants.MAXIMUM_PASSWORD_LENGTH
        )
      );
  }

  @Override
  public Either<Seq<String>, RegistrationAuthenticationRequest> parse(
    RegistrationAuthenticationRequest request
  ) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'parse'");
  }
}
