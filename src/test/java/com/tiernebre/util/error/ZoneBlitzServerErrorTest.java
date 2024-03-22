package com.tiernebre.util.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ZoneBlitzServerErrorTest {

  @Test
  public void equals() {
    assertTrue(
      new ZoneBlitzServerError("a").equals(new ZoneBlitzServerError("a"))
    );
    assertFalse(
      new ZoneBlitzServerError("a").equals(new ZoneBlitzServerError("b"))
    );
  }

  @Test
  public void publicMessage() {
    String message = "Internal Server Error";
    assertFalse(
      new ZoneBlitzServerError(message).publicMessage().contains(message)
    );
    assertEquals(
      new ZoneBlitzServerError(message).publicMessage(),
      "An unexpected server error occurred on our end. Please try again or reach out if this error still happens!"
    );
  }
}
