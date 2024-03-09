package com.tiernebre.authentication.registration;

import io.vavr.control.Option;

public interface RegistrationRepository {
  Registration insertOne(String username, byte[] password);
  Option<Registration> selectOneByUsername(String username);
}
