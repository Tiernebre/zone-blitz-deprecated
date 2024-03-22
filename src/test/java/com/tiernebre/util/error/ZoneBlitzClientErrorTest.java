package com.tiernebre.util.error;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ZoneBlitzClientErrorTest {

  @Test
  public void equals() {
    assertTrue(
      new ZoneBlitzClientError("a").equals(new ZoneBlitzClientError("a"))
    );
    assertFalse(
      new ZoneBlitzClientError("a").equals(new ZoneBlitzClientError("b"))
    );
  }
}
