package com.tiernebre.authentication;

import com.tiernebre.authentication.session.Session;
import io.vavr.control.Either;

/**
 * Authentication API for authenticating a Zone Blitz user.
 */
public interface AuthenticationStrategy<T> {
  /**
   * Creates and returns a Session based on a user provided request.
   *
   * If the request is valid, a Session is returned. If the request is not valid,
   * an error message String will be returned.
   *
   * @param request The user provided request to authenticate.
   * @return Either an error message string if invalid, or a new session if valid.
   */
  public Either<String, Session> authenticate(T request);
}
