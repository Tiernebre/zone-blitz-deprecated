package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.account.AccountService;
import io.vavr.control.Option;

public final class DefaultRegistrationService implements RegistrationService {

  private final RegistrationRepository repository;
  private final PasswordHasher passwordHasher;
  private final AccountService accountService;

  public DefaultRegistrationService(
    RegistrationRepository repository,
    PasswordHasher passwordHasher,
    AccountService accountService
  ) {
    this.repository = repository;
    this.passwordHasher = passwordHasher;
    this.accountService = accountService;
  }

  @Override
  public Registration create(RegistrationRequest request) {
    var registration = repository.insertOne(
      request.username(),
      passwordHasher.hash(request.password())
    );
    accountService.create(registration);
    return registration;
  }

  @Override
  public Option<Registration> getOne(String username, String password) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOne'");
  }
}
