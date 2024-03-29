package com.tiernebre.util.pagination;

import io.javalin.http.Context;
import io.vavr.control.Option;

public final class DefaultWebPaginationHelper implements WebPaginationHelper {

  @Override
  public PageRequest pageRequest(Context ctx) {
    return Option.of(ctx)
      .map(
        context ->
          new PageRequest(
            Option.of(
              context.queryParam(PaginationConstants.FIRST_QUERY_PARAM_NAME)
            )
              .toTry()
              .mapTry(Integer::parseInt)
              .toOption()
              .getOrElse(PaginationConstants.DEFAULT_PAGE_SIZE),
            context.queryParam(PaginationConstants.CURSOR_QUERY_PARAM_NAME)
          )
      )
      .getOrElse(PaginationConstants.DEFAULT_PAGE_REQUEST);
  }
}
