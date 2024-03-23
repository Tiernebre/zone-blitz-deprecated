package com.tiernebre.util.pagination;

public interface CursorMapper {
  public String idToCursor(long id);

  public long cursorToId(String cursor);
}
