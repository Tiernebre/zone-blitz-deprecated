package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.account.AccountService;
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
      .map(
        req ->
          repository.insertOne(
            req.username(),
            passwordHasher.hash(req.password())
          )
      )
      .peek(accountService::create);
  }

  @Override
  public Option<Registration> getOne(String username, String password) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOne'");
  }
}
