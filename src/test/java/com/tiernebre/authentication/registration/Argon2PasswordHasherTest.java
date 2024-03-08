package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class Argon2PasswordHasherTest {

  private final PasswordHasher passwordHasher = new Argon2PasswordHasher();

  @Test
  public void hash() {
    String password = "password";
    String result = passwordHasher.hash(password);
    assertNotNull(result);
    assertNotEquals(password, result);
  }

  @Test
  public void verify() {
    String password = "password";
    String result = passwordHasher.hash(password);
    assertTrue(passwordHasher.verify(password, result));
  }
}
