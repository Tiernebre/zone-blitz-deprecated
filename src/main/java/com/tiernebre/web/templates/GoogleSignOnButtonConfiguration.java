package com.tiernebre.web.templates;

public record GoogleSignOnButtonConfiguration(
  String clientId,
  String loginUri,
  String scriptSrc
) {}
