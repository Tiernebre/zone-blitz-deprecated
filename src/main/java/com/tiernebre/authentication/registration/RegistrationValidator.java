package com.tiernebre.authentication.registration;

import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface RegistrationValidator {
  public Either<Seq<String>, RegistrationRequest> parse(
    CreateRegistrationRequest request
  );

  public Either<Seq<String>, RegistrationAuthenticationRequest> parse(
    RegistrationAuthenticationRequest request
  );
}
