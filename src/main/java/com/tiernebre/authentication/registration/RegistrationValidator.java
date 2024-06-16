package com.tiernebre.authentication.registration;

import com.tiernebre.util.error.ZoneBlitzError;
import io.vavr.control.Either;

public interface RegistrationValidator {
  public Either<ZoneBlitzError, RegistrationRequest> parse(
    CreateRegistrationRequest request
  );
}
