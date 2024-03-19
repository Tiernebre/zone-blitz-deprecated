package com.tiernebre.authentication.registration;

import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.util.error.ZoneBlitzClientError;
import com.tiernebre.util.error.ZoneBlitzError;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class DefaultRegistrationService implements RegistrationService {

  private final RegistrationRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final AccountService accountService;
  private final RegistrationValidator validator;

  public DefaultRegistrationService(
    RegistrationRepository repository,
    PasswordEncoder passwordEncoder,
    AccountService accountService,
    RegistrationValidator validator
  ) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.accountService = accountService;
    this.validator = validator;
  }

  @Override
  public Either<ZoneBlitzError, Registration> create(
    CreateRegistrationRequest request
  ) {
    return validator
      .parse(request)
      .filterOrElse(
        this::doesNotExist,
        __ ->
          new ZoneBlitzClientError(
            "The requested username already exists. Please specify a different username."
          )
      )
      .flatMap(this::persist)
      .peek(accountService::create);
  }

  @Override
  public Option<Registration> getOne(String username, String password) {
    return repository
      .selectOneByUsername(username)
      .filter(
        registration ->
          passwordEncoder.matches(password, registration.password())
      );
  }

  private Either<ZoneBlitzError, Registration> persist(
    RegistrationRequest request
  ) {
    return repository.insertOne(
      request.username(),
      passwordEncoder.encode(request.password())
    );
  }

  private boolean doesNotExist(RegistrationRequest request) {
    return repository.selectOneByUsername(request.username()).isEmpty();
  }
}
