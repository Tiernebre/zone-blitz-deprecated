package com.tiernebre.util.pagination;

import io.javalin.http.Context;

public interface WebPaginationHelper {
  public PageRequest pageRequest(Context ctx);
}
