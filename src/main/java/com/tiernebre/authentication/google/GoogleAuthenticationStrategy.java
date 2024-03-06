package com.tiernebre.authentication.google;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.io.IOException;
import java.security.GeneralSecurityException;

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
  public Either<String, Session> authenticate(
    GoogleAuthenticationRequest request
  ) {
    return Option.of(request)
      .toEither("Request received was null.")
      .filterOrElse(
        this::hasValidCsrfTokens,
        __ -> "Request has invalid CSRF tokens."
      )
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

  private Either<String, GoogleIdToken> fetchIdToken(String credential) {
    Option<GoogleIdToken> token;
    try {
      token = Option.of(verifier.verify(credential));
    } catch (GeneralSecurityException | IOException e) {
      token = Option.none();
    }
    return token.toEither(
      "Google token verifier failed to verify the given credential."
    );
  }
}
