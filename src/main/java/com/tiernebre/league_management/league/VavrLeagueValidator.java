package com.tiernebre.league_management.league;

import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzValidationError;
import com.tiernebre.util.validation.VavrValidationUtils;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;

public final class VavrLeagueValidator implements LeagueValidator {

  private final String NAME_FIELD_NAME = "Name";

  @Override
  public Either<ZoneBlitzError, UserLeagueRequest> validateUserRequest(
    UserLeagueRequest request
  ) {
    return Option.of(request)
      .toValidation("User League Request cannot be null.")
      .map(UserLeagueRequest::name)
      .flatMap(this::validateName)
      .map(UserLeagueRequest::new)
      .toEither()
      .mapLeft(ZoneBlitzValidationError::new);
  }

  private Validation<String, String> validateName(String name) {
    return VavrValidationUtils.required(name, NAME_FIELD_NAME).flatMap(
      VavrValidationUtils.maximumLength(
        NAME_FIELD_NAME,
        LeagueManagementConstants.NAME_MAXIMUM_LENGTH
      )
    );
  }
}
