package com.tiernebre.authentication.registration;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public final class Argon2PasswordHasher implements PasswordHasher {

  private final Argon2PasswordEncoder encoder;

  public Argon2PasswordHasher() {
    encoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
  }

  @Override
  public boolean verify(String givenPassword, String hashedPassword) {
    return encoder.matches(givenPassword, hashedPassword);
  }

  @Override
  public String hash(String password) {
    return encoder.encode(password);
  }
}
