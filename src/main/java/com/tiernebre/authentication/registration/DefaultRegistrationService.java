package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.account.AccountService;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;

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
  public Either<Seq<String>, Registration> create(
    CreateRegistrationRequest request
  ) {
    return validator
      .parse(request)
      .filterOrElse(
        this::doesNotExist,
        __ -> List.of("The requested username already exists.")
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

  private Either<Seq<String>, Registration> persist(
    RegistrationRequest request
  ) {
    return repository
      .insertOne(request.username(), passwordHasher.hash(request.password()))
      .mapLeft(
        __ ->
          List.of("Could not create registration due to an error on our end.")
      );
  }

  private boolean doesNotExist(RegistrationRequest request) {
    return repository.selectOneByUsername(request.username()).isEmpty();
  }
}
