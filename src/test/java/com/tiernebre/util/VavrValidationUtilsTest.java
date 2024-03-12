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
}
