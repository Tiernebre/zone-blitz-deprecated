package com.tiernebre.web.middlewares;

import com.tiernebre.web.constants.WebConstants;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class SecurityMiddleware implements Handler {

  @Override
  public void handle(@NotNull Context ctx) throws Exception {
    if (
      !ctx
        .res()
        .containsHeader(WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME)
    ) {
      ctx.header(
        WebConstants.CONTENT_SECURITY_POLICY_HEADER_NAME,
        WebConstants.CONTENT_SECURITY_POLICY
      );
    }
  }
}
