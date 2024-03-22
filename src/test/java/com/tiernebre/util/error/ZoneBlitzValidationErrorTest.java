package com.tiernebre.util.error;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

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
