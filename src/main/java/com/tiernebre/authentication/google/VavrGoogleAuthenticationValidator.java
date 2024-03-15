package com.tiernebre.authentication.google;

import static com.tiernebre.util.validation.VavrValidationUtils.matches;

import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import com.tiernebre.util.validation.VavrValidationUtils;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import java.util.stream.Collectors;

public class VavrGoogleAuthenticationValidator
  implements GoogleAuthenticationValidator {

  @Override
  public Either<ZoneBlitzError, String> parseCredential(
    GoogleAuthenticationRequest request
  ) {
    return Option.of(request)
      .toValidation(
        (Seq<String>) List.of(
          "Google Authentication Request received was null."
        )
      )
      .flatMap(req -> validateNonNull(req))
      .<ZoneBlitzError>mapError(
        errors ->
          new ZoneBlitzServerError(errors.collect(Collectors.joining(", ")))
      )
      .toEither();
  }

  private Validation<Seq<String>, String> validateNonNull(
    GoogleAuthenticationRequest request
  ) {
    return Validation.combine(
      validateCredential(request.credential()),
      validateCsrfTokens(request.cookieCsrfToken(), request.bodyCrsfToken())
    ).ap((credential, __) -> credential);
  }

  private Validation<String, String> validateCredential(String credential) {
    return VavrValidationUtils.required(credential, "Google Credential token");
  }

  private Validation<String, Tuple2<String, String>> validateCsrfTokens(
    String cookieCsrfToken,
    String bodyCsrfToken
  ) {
    var cookieFieldName = "Google Cookie CSRF token";
    var bodyFieldName = "Google Body CSRF token";
    return Validation.combine(
      VavrValidationUtils.required(cookieCsrfToken, cookieFieldName),
      VavrValidationUtils.required(bodyCsrfToken, bodyFieldName)
    )
      .ap((cookie, body) -> new Tuple2<String, String>(cookie, body))
      .mapError(errors -> errors.collect(Collectors.joining(" ")))
      .flatMap(matches(cookieFieldName, bodyFieldName));
  }
}
