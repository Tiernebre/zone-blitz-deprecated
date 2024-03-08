package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.session.Session;
import io.vavr.control.Either;
import io.vavr.control.Option;

public class RegistrationAuthenticationStrategy
  implements AuthenticationStrategy<RegistrationAuthenticationRequest> {

  private final RegistrationService service;

  public RegistrationAuthenticationStrategy(RegistrationService service) {
    this.service = service;
  }

  @Override
  public Either<String, Session> authenticate(
    RegistrationAuthenticationRequest request
  ) {
    return Option.of(request)
      .toEither("Given registration authentication request was null.")
      .flatMap(
        value ->
          service
            .getOne(value.username(), value.password())
            .toEither(
              "Could not find a registration with the given username and password."
            )
      )
      .map(registration -> new Session(null, 0));
  }
}
