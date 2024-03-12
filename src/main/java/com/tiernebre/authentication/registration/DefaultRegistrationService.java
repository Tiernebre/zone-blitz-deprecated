package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.account.AccountService;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.stream.Collectors;

public final class DefaultRegistrationService implements RegistrationService {

  private final RegistrationRepository repository;
  private final PasswordHasher passwordHasher;
  private final AccountService accountService;
  private final RegistrationValidator validator;

  public DefaultRegistrationService(
    RegistrationRepository repository,
    PasswordHasher passwordHasher,
    AccountService accountService,
    RegistrationValidator validator
  ) {
    this.repository = repository;
    this.passwordHasher = passwordHasher;
    this.accountService = accountService;
    this.validator = validator;
  }

  @Override
  public Either<String, Registration> create(
    CreateRegistrationRequest request
  ) {
    return validator
      .parse(request)
      .mapLeft(errors -> errors.collect(Collectors.joining(",")))
      .filterOrElse(
        this::doesNotExist,
        __ -> "The requested username already exists."
      )
      .flatMap(this::persist)
      .peek(accountService::create);
  }

  @Override
  public Option<Registration> getOne(String username, String password) {
    return repository
      .selectOneByUsername(username)
      .filter(
        registration -> passwordHasher.verify(password, registration.password())
      );
  }

  private Either<String, Registration> persist(RegistrationRequest request) {
    return repository
      .insertOne(request.username(), passwordHasher.hash(request.password()))
      .mapLeft(
        __ -> "Could not create registration due to an error on our end."
      );
  }

  private boolean doesNotExist(RegistrationRequest request) {
    return repository.selectOneByUsername(request.username()).isEmpty();
  }
}
