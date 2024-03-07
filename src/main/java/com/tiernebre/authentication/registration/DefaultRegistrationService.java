package com.tiernebre.authentication.registration;

public final class DefaultRegistrationService implements RegistrationService {

  private final RegistrationRepository repository;

  public DefaultRegistrationService(RegistrationRepository repository) {
    this.repository = repository;
  }

  @Override
  public Registration create(RegistrationRequest request) {
    return repository.insertOne(request.username(), request.password());
  }
}
