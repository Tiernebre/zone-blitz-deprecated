package com.tiernebre.util.pagination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public final class PageMapperTest {

  private final CursorMapper cursorMapper = mock(CursorMapper.class);
  private final PageMapper pageMapper = new PageMapper(cursorMapper);

  @Test
  public void map() {
    var nodes = List.of(new IdentifiableImpl(1L), new IdentifiableImpl(2L));
    var edges = IntStream.range(0, nodes.size())
      .mapToObj(i -> {
        var node = nodes.get(i);
        String cursor = Integer.toString(i);
        when(cursorMapper.toCursor(node)).thenReturn(cursor);
        return new PageEdge<Identifiable>(node, cursor);
      })
      .collect(Collectors.toList());
    var expected = new Page<>(
      edges,
      new PageInfo(edges.getLast().cursor(), false)
    );

    var gotten = pageMapper.map(nodes);
    assertEquals(expected, gotten);
  }
}
