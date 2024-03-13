package com.tiernebre.authentication.registration;

import com.tiernebre.util.error.ZoneBlitzError;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface RegistrationRepository {
  Either<ZoneBlitzError, Registration> insertOne(
    String username,
    byte[] password
  );
  Option<Registration> selectOneByUsername(String username);
}
