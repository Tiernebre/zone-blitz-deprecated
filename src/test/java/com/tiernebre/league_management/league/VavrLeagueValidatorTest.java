package com.tiernebre.league_management.league;

import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzValidationError;
import io.vavr.collection.List;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

public final class VavrLeagueValidatorTest {

  LeagueValidator validator = new VavrLeagueValidator();

  @Test
  public void validateUserRequest() {
    TestCaseRunner.run(
      VavrLeagueValidatorTest.class,
      List.of(
        new TestCase<
          UserLeagueRequest,
          Either<ZoneBlitzError, UserLeagueRequest>
        >(
          "null request",
          null,
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                "User League Request cannot be null."
              )
            )
        ),
        new TestCase<
          UserLeagueRequest,
          Either<ZoneBlitzError, UserLeagueRequest>
        >(
          "null name",
          new UserLeagueRequest(null),
          __ ->
            Either.left(
              new ZoneBlitzValidationError("Name is a required field.")
            )
        ),
        new TestCase<
          UserLeagueRequest,
          Either<ZoneBlitzError, UserLeagueRequest>
        >(
          "empty name",
          new UserLeagueRequest(""),
          __ ->
            Either.left(
              new ZoneBlitzValidationError("Name is a required field.")
            )
        ),
        new TestCase<
          UserLeagueRequest,
          Either<ZoneBlitzError, UserLeagueRequest>
        >(
          "blank name",
          new UserLeagueRequest(" "),
          __ ->
            Either.left(
              new ZoneBlitzValidationError("Name is a required field.")
            )
        ),
        new TestCase<
          UserLeagueRequest,
          Either<ZoneBlitzError, UserLeagueRequest>
        >(
          "long name",
          new UserLeagueRequest("a".repeat(65)),
          __ ->
            Either.left(
              new ZoneBlitzValidationError(
                "Name cannot be greater than 64 characters long."
              )
            )
        ),
        new TestCase<
          UserLeagueRequest,
          Either<ZoneBlitzError, UserLeagueRequest>
        >(
          "valid request",
          new UserLeagueRequest("league"),
          input -> Either.right(input)
        )
      ),
      validator::validateUserRequest
    );
  }
}
