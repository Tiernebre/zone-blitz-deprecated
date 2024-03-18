package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public final class Argon2PasswordHasherTest {

  private final PasswordHasher passwordHasher = new Argon2PasswordHasher();

  @Test
  public void hash() {
    var password = "password";
    var result = passwordHasher.hash(password);
    assertNotNull(result);
    assertNotEquals(password, result);
  }

  @Test
  public void verifyValid() {
    var password = "password";
    var result = passwordHasher.hash(password);
    assertTrue(passwordHasher.verify(password, result));
  }

  @Test
  public void verifyInvalid() {
    var password = "password";
    var result = passwordHasher.hash(password);
    assertFalse(passwordHasher.verify(password + "f", result));
  }
}
