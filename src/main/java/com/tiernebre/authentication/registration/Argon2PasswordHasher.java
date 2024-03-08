package com.tiernebre.authentication.registration;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

public final class Argon2PasswordHasher implements PasswordHasher {

  private final int iterations = 2;
  private final int memoryLimitAsKB = 19923;
  private final int parallelism = 1;
  private final int hashLength = 32;

  private final Argon2BytesGenerator generator;

  public Argon2PasswordHasher() {
    generator = new Argon2BytesGenerator();
    generator.init(
      new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
        .withVersion(Argon2Parameters.ARGON2_VERSION_13)
        .withIterations(iterations)
        .withMemoryAsKB(memoryLimitAsKB)
        .withParallelism(parallelism)
        .withSalt(generateSalt16Byte())
        .build()
    );
  }

  @Override
  public boolean verify(String givenPassword, String hashedPassword) {
    return hash(givenPassword).equals(hashedPassword);
  }

  @Override
  public String hash(String password) {
    byte[] result = new byte[hashLength];
    generator.generateBytes(password.getBytes(StandardCharsets.UTF_8), result);
    return new String(result, StandardCharsets.UTF_8);
  }

  private byte[] generateSalt16Byte() {
    var secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }
}
