package com.tiernebre.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tiernebre.util.validation.VavrValidationUtils;
import org.junit.Test;

public class VavrValidationUtilsTest {

  @Test
  public void required() {
    String expectedErrorMessage = "Expected Error";
    assertTrue(
      VavrValidationUtils.required(expectedErrorMessage).apply("").isInvalid()
    );
    assertEquals(
      VavrValidationUtils.required(expectedErrorMessage).apply("").getError(),
      expectedErrorMessage
    );
    assertTrue(
      VavrValidationUtils.required("", expectedErrorMessage).isInvalid()
    );
    assertTrue(
      VavrValidationUtils.required(expectedErrorMessage).apply(" ").isInvalid()
    );
    assertTrue(
      VavrValidationUtils.required(" ", expectedErrorMessage).isInvalid()
    );
    assertTrue(
      VavrValidationUtils.required(expectedErrorMessage).apply(null).isInvalid()
    );
    assertTrue(
      VavrValidationUtils.required(null, expectedErrorMessage).isInvalid()
    );
    assertTrue(
      VavrValidationUtils.required(expectedErrorMessage).apply("1").isValid()
    );
    assertTrue(
      VavrValidationUtils.required("1", expectedErrorMessage).isValid()
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
