package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.util.error.ZoneBlitzClientError;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
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
  public Either<ZoneBlitzError, Session> authenticate(
    RegistrationAuthenticationRequest request
  ) {
    return Option.of(request)
      .<ZoneBlitzError>toEither(
        new ZoneBlitzClientError(
          "Given registration authentication request was null."
        )
      )
      .flatMap(this::getRegistration)
      .flatMap(this::getAccount)
      .map(sessionService::create);
  }

  private Either<ZoneBlitzError, Registration> getRegistration(
    RegistrationAuthenticationRequest request
  ) {
    return service
      .getOne(request.username(), request.password())
      .<ZoneBlitzError>toEither(
        new ZoneBlitzClientError(
          "Could not find a registration with the given username and password."
        )
      );
  }

  private Either<ZoneBlitzError, Account> getAccount(
    Registration registration
  ) {
    return accountService
      .getForRegistration(registration.id())
      .<ZoneBlitzError>toEither(
        new ZoneBlitzServerError(
          "Could not find an associated account for the provided registration."
        )
      );
  }
}
