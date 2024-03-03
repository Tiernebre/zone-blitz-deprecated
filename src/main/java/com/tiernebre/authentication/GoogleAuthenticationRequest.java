package com.tiernebre.authentication;

public record GoogleAuthenticationRequest(
  String credential,
  String bodyCrsfToken,
  String cookieCsrfToken
) {}
