package com.tiernebre.util.error;

import static org.junit.Assert.assertEquals;

import io.vavr.collection.List;
import org.junit.Test;

public class ZoneBlitzValidationErrorTest {

  @Test
  public void publicMessage() {
    assertEquals(
      "Validation Error 1, Validation Error 2",
      new ZoneBlitzValidationError(
        List.of("Validation Error 1", "Validation Error 2")
      ).publicMessage()
    );
  }
}
