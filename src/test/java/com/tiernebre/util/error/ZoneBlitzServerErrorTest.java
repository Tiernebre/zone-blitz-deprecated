package com.tiernebre.util.error;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
