package com.tiernebre.util.pagination;

import io.javalin.http.Context;
import io.vavr.control.Option;

public final class DefaultWebPaginationHelper implements WebPaginationHelper {

  @Override
  public PageRequest pageRequest(Context ctx) {
    return new PageRequest(
      Option.of(ctx.queryParam(PaginationConstants.FIRST_QUERY_PARAM_NAME))
        .toTry()
        .mapTry(Integer::parseInt)
        .toOption()
        .getOrElse(PaginationConstants.DEFAULT_PAGE_SIZE),
      ctx.queryParam(PaginationConstants.CURSOR_QUERY_PARAM_NAME)
    );
  }
}
