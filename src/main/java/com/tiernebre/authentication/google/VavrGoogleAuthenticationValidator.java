package com.tiernebre.authentication.google;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

public class VavrGoogleAuthenticationValidator
  implements GoogleAuthenticationValidator {

  @Override
  public Either<Seq<String>, String> parseCredential(
    GoogleAuthenticationRequest request
  ) {
    return Option.of(request)
      .toValidation(
        (Seq<String>) List.of(
          "Google Authentication Request received was null."
        )
      )
      .flatMap(req -> this.validateNonNull(req))
      .toEither();
  }

  public Validation<Seq<String>, String> validateNonNull(
    GoogleAuthenticationRequest request
  ) {
    return Validation.combine(
      validateCredential(request.credential()),
      validateCsrfTokens(request.cookieCsrfToken(), request.bodyCrsfToken())
    ).ap((credential, __) -> credential);
  }

  private Validation<String, String> validateCredential(String credential) {
    return StringUtils.isBlank(credential)
      ? Validation.invalid("Google Credential received was an empty string.")
      : Validation.valid(credential);
  }

  private Validation<String, String> validateCsrfTokens(
    String cookieCsrfToken,
    String bodyCsrfToken
  ) {
    return Validation.<String, String>valid(cookieCsrfToken)
      .flatMap(
        token ->
          StringUtils.isNoneBlank(token, bodyCsrfToken)
            ? Validation.valid(token)
            : Validation.invalid(
              "Google CSRF token received was an empty string."
            )
      )
      .flatMap(
        token ->
          token.equals(bodyCsrfToken)
            ? Validation.valid(token)
            : Validation.invalid("Google CSRF tokens do not match each other.")
      );
  }
}
