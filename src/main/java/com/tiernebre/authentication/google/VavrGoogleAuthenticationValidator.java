package com.tiernebre.authentication.google;

import com.tiernebre.util.error.ZoneBlitzServerError;
import com.tiernebre.util.validation.VavrValidationUtils;
import io.javalin.validation.Validator;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class VavrGoogleAuthenticationValidator
  implements GoogleAuthenticationValidator {

  @Override
  public Either<ZoneBlitzServerError, String> parseCredential(
    GoogleAuthenticationRequest request
  ) {
    return Option.of(request)
      .toValidation(
        (Seq<String>) List.of(
          "Google Authentication Request received was null."
        )
      )
      .flatMap(req -> this.validateNonNull(req))
      .mapError(
        errors ->
          new ZoneBlitzServerError(errors.collect(Collectors.joining("\n")))
      )
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
    return VavrValidationUtils.required(credential, "Google Credential Token");
  }

  private Validation<String, String> validateCsrfTokens(
    String cookieCsrfToken,
    String bodyCsrfToken
  ) {
    return Validation.combine(
      VavrValidationUtils.required(cookieCsrfToken, "Google Cookie CSRF token"),
      VavrValidationUtils.required(bodyCsrfToken, "Google Body CSRF token")
    )
      .ap((cookie, body) -> new Tuple2<>(cookie, body))
      .map(
        tokens ->
          tokens._1.equals(tokens._2)
            ? Validation.valid(tokens._1)
            : Validation.invalid("Google CSRF tokens do not match each other.")
      )
      .mapError(errors -> errors.collect(Collectors.joining(", ")));
  }
}
