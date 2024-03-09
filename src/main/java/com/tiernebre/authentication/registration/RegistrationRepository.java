package com.tiernebre.authentication.registration;

import io.vavr.control.Option;

public interface RegistrationRepository {
  Registration insertOne(String username, String password);
  Option<Registration> selectOneByUsername(String username);
}
