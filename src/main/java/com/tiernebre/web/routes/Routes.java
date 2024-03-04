package com.tiernebre.web.routes;

import com.tiernebre.web.routes.api.ApiRoutes;
import com.tiernebre.web.routes.page.PageRoutes;
import io.javalin.apibuilder.EndpointGroup;

public final class Routes implements EndpointGroup {

  private final ApiRoutes apiRoutes;
  private final PageRoutes pageRoutes;

  public Routes(ApiRoutes apiRoutes, PageRoutes pageRoutes) {
    this.apiRoutes = apiRoutes;
    this.pageRoutes = pageRoutes;
  }

  @Override
  public void addEndpoints() {
    pageRoutes.addEndpoints();
    apiRoutes.addEndpoints();
  }
}
