package com.tiernebre.authentication.registration;

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
}
