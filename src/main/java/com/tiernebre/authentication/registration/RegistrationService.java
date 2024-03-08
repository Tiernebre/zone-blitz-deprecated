package com.tiernebre.authentication.registration;

import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface RegistrationService {
  public Either<Seq<String>, Registration> create(
    CreateRegistrationRequest request
  );

  public Option<Registration> getOne(String username, String password);
}
