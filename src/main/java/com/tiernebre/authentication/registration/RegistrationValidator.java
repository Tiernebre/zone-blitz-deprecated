package com.tiernebre.authentication.registration;

import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface RegistrationValidator {
  public Either<Seq<String>, RegistrationRequest> validate(
    String username,
    String password,
    String confirmPassword
  );
}
