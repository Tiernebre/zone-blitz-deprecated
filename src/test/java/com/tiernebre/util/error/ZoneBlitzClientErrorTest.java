package com.tiernebre.util.error;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javalin.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class ZoneBlitzClientErrorTest {

  @Test
  public void equals() {
    assertTrue(
      new ZoneBlitzClientError("a", HttpStatus.BAD_REQUEST).equals(
        new ZoneBlitzClientError("a", HttpStatus.BAD_REQUEST)
      )
    );
    assertFalse(
      new ZoneBlitzClientError("a", HttpStatus.BAD_REQUEST).equals(
        new ZoneBlitzClientError("b", HttpStatus.BAD_REQUEST)
      )
    );
    assertFalse(
      new ZoneBlitzClientError("a", HttpStatus.BAD_REQUEST).equals(
        new ZoneBlitzClientError("a", HttpStatus.CONFLICT)
      )
    );
  }
}
