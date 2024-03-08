package com.tiernebre.authentication.registration;

import io.vavr.control.Option;

public final class DefaultRegistrationService implements RegistrationService {

  private final RegistrationRepository repository;
  private final PasswordHasher passwordHasher;

  public DefaultRegistrationService(
    RegistrationRepository repository,
    PasswordHasher passwordHasher
  ) {
    this.repository = repository;
    this.passwordHasher = passwordHasher;
  }

  @Override
  public Registration create(RegistrationRequest request) {
    return repository.insertOne(
      request.username(),
      passwordHasher.hash(request.password())
    );
  }

  @Override
  public Option<Registration> getOne(String username, String password) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOne'");
  }
}
