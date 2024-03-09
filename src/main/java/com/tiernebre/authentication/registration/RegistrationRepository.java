package com.tiernebre.authentication.registration;

import io.vavr.control.Either;
import io.vavr.control.Option;

public interface RegistrationRepository {
  Either<Throwable, Registration> insertOne(String username, byte[] password);
  Option<Registration> selectOneByUsername(String username);
}
