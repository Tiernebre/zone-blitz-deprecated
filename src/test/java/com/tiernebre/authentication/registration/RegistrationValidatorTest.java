package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertEquals;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.Test;

public final class RegistrationValidatorTest {

  private final RegistrationValidator validator = new RegistrationValidator();

  private final record Case(
    String name,
    String username,
    String password,
    Validation<Seq<String>, RegistrationRequest> expected
  ) {}

  @Test
  public void validate() {
    var cases = new Case[] {
      new Case(
        "null username",
        null,
        "password",
        Validation.invalid(List.of("Username is a required field."))
      ),
    };
    for (var test : cases) {
      assertEquals(
        test.expected(),
        validator.validate(test.username(), test.password())
      );
    }
  }
}
