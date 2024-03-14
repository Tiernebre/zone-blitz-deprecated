package com.tiernebre.authentication.google;

import com.tiernebre.util.error.ZoneBlitzError;
import io.vavr.control.Either;

public interface GoogleAuthenticationValidator {
  /**
   * For a given GoogleAuthenticationRequest, verifies that is a safe and valid request
   * and parses the specific token credential.
   *
   * @param request The authentication request from Google.
   * @return Either the validation error or a valid credential token string.
   */
  public Either<ZoneBlitzError, String> parseCredential(
    GoogleAuthenticationRequest request
  );
}
