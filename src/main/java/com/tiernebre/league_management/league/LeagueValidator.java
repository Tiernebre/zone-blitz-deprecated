package com.tiernebre.league_management.league;

import com.tiernebre.util.error.ZoneBlitzError;
import io.vavr.control.Either;

public interface LeagueValidator {
  public Either<ZoneBlitzError, UserLeagueRequest> validateUserRequest(
    UserLeagueRequest request
  );
}
