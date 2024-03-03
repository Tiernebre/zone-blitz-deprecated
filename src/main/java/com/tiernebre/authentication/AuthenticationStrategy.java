package com.tiernebre.authentication;

import com.tiernebre.authentication.session.Session;
import java.util.Optional;

public interface AuthenticationStrategy<T> {
  public Optional<Session> authenticate(T request);
}
