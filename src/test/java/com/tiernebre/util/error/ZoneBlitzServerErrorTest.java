package com.tiernebre.util.error;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.tiernebre.util.validation.error.ZoneBlitzServerError;
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
}
