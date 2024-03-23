package com.tiernebre.util.pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import io.vavr.collection.List;
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

  @Test
  public void mapsEmptyStrings() {
    TestCaseRunner.run(
      Base64CursorMapperTest.class,
      List.of(
        new TestCase<String, Long>("null", null, __ -> 0L),
        new TestCase<String, Long>("empty", "", __ -> 0L),
        new TestCase<String, Long>("blank", " ", __ -> 0L)
      ),
      base64CursorMapper::cursorToId
    );
  }
}
