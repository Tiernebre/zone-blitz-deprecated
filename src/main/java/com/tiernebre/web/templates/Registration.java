package com.tiernebre.web.templates;

import io.jstach.jstache.JStache;

@JStache(path = "registration")
public record Registration(
  AuthenticationForm form,
  String confirmPasswordFieldName,
  String error,
  GoogleSignOnButtonConfiguration google
) {}
