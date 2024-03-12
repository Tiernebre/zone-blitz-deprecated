package com.tiernebre.authentication.registration;

import com.tiernebre.util.validation.error.ZoneBlitzError;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface RegistrationService {
  public Either<ZoneBlitzError, Registration> create(
    CreateRegistrationRequest request
  );

  public Option<Registration> getOne(String username, String password);
}
