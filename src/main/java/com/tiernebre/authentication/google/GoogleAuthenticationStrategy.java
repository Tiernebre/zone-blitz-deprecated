package com.tiernebre.authentication.google;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;

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
      .flatMap(this::verifyAndParseCredential)
      .map(token -> {
        return token.getPayload();
      })
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

  private Either<String, GoogleIdToken> verifyAndParseCredential(
    String credential
  ) {
    return Option.of(credential)
      .toTry()
      .mapTry(verifier::verify)
      .filter(token -> token != null)
      .toEither(
        "Could not verify and parse given Google authentication credential."
      );
  }
}
