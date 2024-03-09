package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;

public final class Argon2PasswordHasherTest {

  private final PasswordHasher passwordHasher = new Argon2PasswordHasher();

  @Test
  public void hash() {
    var password = "password";
    var result = passwordHasher.hash(password);
    assertNotNull(result);
    assertFalse(Arrays.equals(password.getBytes(), result));
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
