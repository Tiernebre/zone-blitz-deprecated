package com.tiernebre.util.pagination;

public interface CursorMapper {
  public String toCursor(Identifiable node);

  public String idToCursor(long id);

  public long cursorToId(String cursor);
}
