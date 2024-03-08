package com.tiernebre.authentication.registration;

public interface PasswordHasher {
  /**
   * Hashes the given password into a one-way hash.
   * @param password Provided plaintext unhashed password.
   * @return A hashed version of the password.
   */
  public String hash(String password);

  /**
   * Verifies that a given password matches a hashed password.
   * @param givenPassword The plaintext unhashed password to verify.
   * @param hashedPassword The hashed password to check against.
   * @return true if the passwords are equal, false if they do not match.
   */
  public boolean verify(String givenPassword, String hashedPassword);
}
