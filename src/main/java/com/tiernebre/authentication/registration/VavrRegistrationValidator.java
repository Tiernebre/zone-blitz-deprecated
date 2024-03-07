package com.tiernebre.authentication.registration;

import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

public final class VavrRegistrationValidator implements RegistrationValidator {

  public Either<Seq<String>, RegistrationRequest> validate(
    String username,
    String password
  ) {
    return Validation.combine(
      validateUsername(username),
      validatePassword(password)
    )
      .ap(RegistrationRequest::new)
      .toEither();
  }

  private Validation<String, String> validateUsername(String username) {
    return StringUtils.isBlank(username)
      ? Validation.invalid("Username is a required field.")
      : Validation.valid(username);
  }

  private Validation<String, String> validatePassword(String password) {
    return StringUtils.isBlank(password)
      ? Validation.invalid("Password is a required field.")
      : Validation.valid(password);
  }
}
