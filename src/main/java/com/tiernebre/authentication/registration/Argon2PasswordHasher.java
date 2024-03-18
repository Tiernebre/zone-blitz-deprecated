package com.tiernebre.authentication.registration;

import java.util.Arrays;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;

public final class Argon2PasswordHasher implements PasswordHasher {

  private final int iterations = 2;
  private final int memoryLimitAsKB = 19923;
  private final int parallelism = 5;
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
        .build()
    );
  }

  @Override
  public boolean verify(String givenPassword, byte[] hashedPassword) {
    return Arrays.equals(hash(givenPassword), hashedPassword);
  }

  @Override
  public byte[] hash(String password) {
    byte[] result = new byte[hashLength];
    generator.generateBytes(password.toCharArray(), result);
    return result;
  }
}
