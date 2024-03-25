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
        )
      ),
      validator::validateUserRequest
    );
  }
}
