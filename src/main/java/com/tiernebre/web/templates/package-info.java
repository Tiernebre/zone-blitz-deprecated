/**
 * Collection of HTML templates used for rendering web pages on Zone Blitz.
 */
@io.jstach.jstache.JStachePath(prefix = "templates/", suffix = ".mustache")
@io.jstach.jstache.JStachePartials(
  {
    @io.jstach.jstache.JStachePartial(
      name = "base_layout",
      path = "partials/base_layout"
    ),
    @io.jstach.jstache.JStachePartial(
      name = "interactive_layout",
      path = "partials/interactive_layout"
    ),
    @io.jstach.jstache.JStachePartial(name = "page", path = "partials/page"),
    @io.jstach.jstache.JStachePartial(
      name = "button",
      path = "components/button"
    ),
    @io.jstach.jstache.JStachePartial(
      name = "button_link",
      path = "components/button_link"
    ),
  }
)
package com.tiernebre.web.templates;
