package com.tiernebre.authentication.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionRepository;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GoogleAuthenticationStrategy
  implements AuthenticationStrategy<GoogleAuthenticationRequest> {

  private static final Logger LOG = LoggerFactory.getLogger(
    GoogleAuthenticationStrategy.class
  );

  private final GoogleIdTokenVerifier verifier;
  private final SessionRepository sessionRepository;

  public GoogleAuthenticationStrategy(
    GoogleIdTokenVerifier verifier,
    SessionRepository sessionRepository
  ) {
    this.verifier = verifier;
    this.sessionRepository = sessionRepository;
  }

  @Override
  public Optional<Session> authenticate(GoogleAuthenticationRequest request) {
    if (request == null) {
      return Optional.empty();
    }

    var bodyCrsfToken = request.bodyCrsfToken();
    var cookieCsrfToken = request.cookieCsrfToken();

    if (
      bodyCrsfToken == null ||
      cookieCsrfToken == null ||
      !bodyCrsfToken.equals(cookieCsrfToken)
    ) {
      LOG.debug(
        "No or invalid CSRF token provided on Google authentication attempt."
      );
      return Optional.empty();
    }

    GoogleIdToken token;
    try {
      token = verifier.verify(request.credential());
    } catch (GeneralSecurityException | IOException e) {
      LOG.debug(
        "Token provided in Google sign on request was not able to be parsed."
      );
      return Optional.empty();
    }

    if (token != null) {
      LOG.debug(
        "Successfully processed and verified Google sign on token that was valid."
      );
      return Optional.of(
        sessionRepository.insertOne(token.getPayload().getSubject())
      );
    } else {
      LOG.debug("Token provided was not parsed.");
      return Optional.empty();
    }
  }
}
