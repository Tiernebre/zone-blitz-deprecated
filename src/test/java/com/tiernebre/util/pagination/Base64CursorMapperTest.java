package com.tiernebre.util.pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Base64;
import org.junit.jupiter.api.Test;

public final class Base64CursorMapperTest {

  CursorMapper base64CursorMapper = new Base64CursorMapper(
    Base64.getEncoder(),
    Base64.getDecoder()
  );

  @Test
  public void maps() {
    long id = 1L;
    String cursor = base64CursorMapper.idToCursor(id);
    assertNotEquals(Long.toString(id), cursor);
    long mappedBack = base64CursorMapper.cursorToId(cursor);
    assertEquals(id, mappedBack);
  }
}
