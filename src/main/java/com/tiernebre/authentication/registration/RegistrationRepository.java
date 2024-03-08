package com.tiernebre.authentication.registration;

public interface RegistrationRepository {
  Registration insertOne(String username, String password);
}
