package com.tiernebre.util.error;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
