package com.tiernebre.util.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.tiernebre.util.validation.error.ZoneBlitzClientError;
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
