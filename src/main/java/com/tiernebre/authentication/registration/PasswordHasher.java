package com.tiernebre.authentication.registration;

public interface PasswordHasher {
  public String hash(String password);
}
