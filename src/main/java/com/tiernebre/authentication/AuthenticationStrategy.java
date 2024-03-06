package com.tiernebre.authentication;

import com.tiernebre.authentication.session.Session;
import io.vavr.control.Either;

public interface AuthenticationStrategy<T> {
  public Either<String, Session> authenticate(T request);
}
