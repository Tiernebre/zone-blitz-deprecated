package com.tiernebre.util.pagination;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class PageMapper {

  private final CursorMapper cursorMapper;

  public PageMapper(CursorMapper cursorMapper) {
    this.cursorMapper = cursorMapper;
  }

  public <T extends Identifiable> Page<T> map(Collection<T> nodes) {
    List<PageEdge<T>> edges = nodes
      .stream()
      .map(this::mapNode)
      .collect(Collectors.toList());
    return new Page<T>(edges, new PageInfo(edges.getLast().cursor(), false));
  }

  private <T extends Identifiable> PageEdge<T> mapNode(T node) {
    return new PageEdge<T>(node, cursorMapper.idToCursor(node.id()));
  }
}
