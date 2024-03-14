package com.tiernebre.authentication.google;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.tiernebre.authentication.AuthenticationStrategy;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.vavr.control.Either;
import io.vavr.control.Try;

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
  public Either<ZoneBlitzError, Session> authenticate(
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

  private Either<ZoneBlitzError, GoogleIdToken> verifyAndParseCredential(
    String credential
  ) {
    return Try.of(() -> verifier.verify(credential))
      .filter(token -> token != null)
      .toEither(
        new ZoneBlitzServerError(
          "Could not verify and parse given Google authentication credential."
        )
      );
  }
}
