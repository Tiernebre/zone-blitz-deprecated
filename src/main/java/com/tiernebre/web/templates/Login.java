package com.tiernebre.web.templates;

import io.jstach.jstache.JStache;

@JStache(path = "login")
public record Login(
  String clientId,
  String loginUri,
  String googleScriptSrc,
  AuthenticationForm form
) {}
