package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import io.vavr.control.Either;
import io.vavr.control.Option;

public class RegistrationAuthenticationStrategy
  implements AuthenticationStrategy<RegistrationAuthenticationRequest> {

  private final RegistrationService service;
  private final AccountService accountService;
  private final SessionService sessionService;

  public RegistrationAuthenticationStrategy(
    RegistrationService service,
    AccountService accountService,
    SessionService sessionService
  ) {
    this.service = service;
    this.accountService = accountService;
    this.sessionService = sessionService;
  }

  @Override
  public Either<String, Session> authenticate(
    RegistrationAuthenticationRequest request
  ) {
    return Option.of(request)
      .toEither("Given registration authentication request was null.")
      .flatMap(this::getRegistration)
      .flatMap(this::getAccount)
      .map(sessionService::create);
  }

  private Either<String, Registration> getRegistration(
    RegistrationAuthenticationRequest request
  ) {
    return service
      .getOne(request.username(), request.password())
      .toEither(
        "Could not find a registration with the given username and password."
      );
  }

  private Either<String, Account> getAccount(Registration registration) {
    return accountService
      .getForRegistration(registration.id())
      .toEither(
        "Could not find an associated account for the provided registration."
      );
  }
}
