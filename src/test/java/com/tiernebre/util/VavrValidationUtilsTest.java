package com.tiernebre.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.validation.VavrValidationUtils;
import io.vavr.collection.List;
import io.vavr.control.Validation;
import org.junit.Test;

public class VavrValidationUtilsTest {

  @Test
  public void required() {
    var fieldName = "requiredField";
    TestCaseRunner.run(
      VavrValidationUtils.class,
      List.of(
        new TestCase<String, Validation<String, String>>(
          "null",
          null,
          __ ->
            Validation.invalid(
              String.format("%s is a required field.", fieldName)
            )
        ),
        new TestCase<String, Validation<String, String>>(
          "empty",
          "",
          __ ->
            Validation.invalid(
              String.format("%s is a required field.", fieldName)
            )
        ),
        new TestCase<String, Validation<String, String>>(
          "blank",
          " ",
          __ ->
            Validation.invalid(
              String.format("%s is a required field.", fieldName)
            )
        ),
        new TestCase<String, Validation<String, String>>(
          "filled out",
          "a",
          input -> Validation.valid(input)
        )
      ),
      input -> VavrValidationUtils.required(fieldName).apply(input)
    );
  }

  @Test
  public void maximumLength() {
    String expectedErrorMessage = "Expected Error";
    assertTrue(
      VavrValidationUtils.maximumLength(1, expectedErrorMessage)
        .apply("12")
        .isInvalid()
    );
    assertEquals(
      VavrValidationUtils.maximumLength(1, expectedErrorMessage)
        .apply("12")
        .getError(),
      expectedErrorMessage
    );
    assertTrue(
      VavrValidationUtils.maximumLength(1, expectedErrorMessage)
        .apply("1")
        .isValid()
    );
  }

  @Test
  public void minimumLength() {
    String expectedErrorMessage = "Expected Error";
    assertTrue(
      VavrValidationUtils.minimumLength(2, expectedErrorMessage)
        .apply("1")
        .isInvalid()
    );
    assertEquals(
      VavrValidationUtils.minimumLength(2, expectedErrorMessage)
        .apply("1")
        .getError(),
      expectedErrorMessage
    );
    assertTrue(
      VavrValidationUtils.minimumLength(2, expectedErrorMessage)
        .apply("12")
        .isValid()
    );
    assertTrue(
      VavrValidationUtils.minimumLength(2, expectedErrorMessage)
        .apply("123")
        .isValid()
    );
  }
}
