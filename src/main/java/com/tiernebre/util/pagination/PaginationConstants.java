package com.tiernebre.util.pagination;

public final class PaginationConstants {

  public static int DEFAULT_PAGE_SIZE = 20;
  public static int MAX_PAGE_SIZE = 2000;
  public static int MIN_PAGE_SIZE = 0;

  public static PageRequest DEFAULT_PAGE_REQUEST = new PageRequest(
    DEFAULT_PAGE_SIZE,
    null
  );
}
