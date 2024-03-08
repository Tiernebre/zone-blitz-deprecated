package com.tiernebre.authentication.google;

import static org.junit.Assert.assertEquals;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import org.junit.Test;

public final class VavrGoogleAuthenticationValidatorTest {

  private final VavrGoogleAuthenticationValidator validator =
    new VavrGoogleAuthenticationValidator();

  private final record Case(
    String name,
    GoogleAuthenticationRequest request,
    Either<Seq<String>, String> expected
  ) {}

  @Test
  public void cases() throws Exception {
    var cases = new Case[] {
      new Case(
        "Null request",
        null,
        Either.left(List.of("Google Authentication Request received was null."))
      ),
    };
    for (var test : cases) {
      assertEquals(test.expected(), validator.parseCredential(test.request()));
    }
  }
}
