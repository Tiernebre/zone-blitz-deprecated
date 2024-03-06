package com.tiernebre.authentication.google;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionRepository;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

public final class GoogleAuthenticationStrategy
  implements AuthenticationStrategy<GoogleAuthenticationRequest> {

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
    return Optional.ofNullable(request)
      .filter(this::hasValidCsrfTokens)
      .map(GoogleAuthenticationRequest::credential)
      .flatMap(this::fetchIdToken)
      .map(GoogleIdToken::getPayload)
      .map(Payload::getSubject)
      .map(sessionRepository::insertOne);
  }

  private boolean hasValidCsrfTokens(GoogleAuthenticationRequest request) {
    var bodyCrsfToken = request.bodyCrsfToken();
    var cookieCsrfToken = request.cookieCsrfToken();
    return (
      bodyCrsfToken != null &&
      cookieCsrfToken != null &&
      bodyCrsfToken.equals(cookieCsrfToken)
    );
  }

  private Optional<GoogleIdToken> fetchIdToken(String credential) {
    try {
      return Optional.ofNullable(verifier.verify(credential));
    } catch (GeneralSecurityException | IOException e) {
      return Optional.empty();
    }
  }
}
