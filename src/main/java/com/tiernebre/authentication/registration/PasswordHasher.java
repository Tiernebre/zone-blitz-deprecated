package com.tiernebre.authentication.registration;

public interface PasswordHasher {
  public String hash(String password);

  public boolean verify(String givenPassword, String hashedPassword);
}
