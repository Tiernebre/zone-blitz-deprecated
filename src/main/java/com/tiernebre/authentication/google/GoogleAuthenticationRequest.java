package com.tiernebre.authentication.google;

public record GoogleAuthenticationRequest(
  String credential,
  String bodyCrsfToken,
  String cookieCsrfToken
) {}
