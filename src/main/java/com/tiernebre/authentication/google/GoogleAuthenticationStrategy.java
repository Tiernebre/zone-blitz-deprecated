package com.tiernebre.authentication.google;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import io.vavr.control.Either;
import io.vavr.control.Option;

public final class GoogleAuthenticationStrategy
  implements AuthenticationStrategy<GoogleAuthenticationRequest> {

  private final GoogleIdTokenVerifier verifier;
  private final AccountService accountService;
  private final SessionService sessionService;
  private final GoogleAuthenticationValidator validator;

  public GoogleAuthenticationStrategy(
    GoogleIdTokenVerifier verifier,
    SessionService sessionService,
    AccountService accountService,
    GoogleAuthenticationValidator validator
  ) {
    this.verifier = verifier;
    this.sessionService = sessionService;
    this.accountService = accountService;
    this.validator = validator;
  }

  @Override
  public Either<String, Session> authenticate(
    GoogleAuthenticationRequest request
  ) {
    return validator
      .parseCredential(request)
      .flatMap(this::verifyAndParseCredential)
      .map(GoogleIdToken::getPayload)
      .map(Payload::getSubject)
      .flatMap(accountService::getForGoogleAccount)
      .map(sessionService::create);
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
