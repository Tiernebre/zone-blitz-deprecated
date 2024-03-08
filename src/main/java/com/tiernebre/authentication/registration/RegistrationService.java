package com.tiernebre.authentication.registration;

import io.vavr.control.Option;

public interface RegistrationService {
  public Registration create(RegistrationRequest request);

  public Option<Registration> getOne(String username, String password);
}
