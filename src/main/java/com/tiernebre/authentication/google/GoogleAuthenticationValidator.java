package com.tiernebre.authentication.google;

import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface GoogleAuthenticationValidator {
  /**
   * For a given GoogleAuthenticationRequest, verifies that is a safe and valid request
   * and parses the specific token credential.
   *
   * @param request The authentication request from Google.
   * @return Either a list of validation errors or a valid credential token string.
   */
  public Either<Seq<String>, String> parseCredential(
    GoogleAuthenticationRequest request
  );
}
