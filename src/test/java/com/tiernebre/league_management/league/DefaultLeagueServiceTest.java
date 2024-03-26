package com.tiernebre.league_management.league;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzValidationError;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

public final class DefaultLeagueServiceTest {

  private final LeagueRepository repository = mock(LeagueRepository.class);
  private final LeagueValidator validator = mock(LeagueValidator.class);
  private final LeagueService service = new DefaultLeagueService(
    repository,
    validator
  );

  @Test
  public void create() {
    TestCaseRunner.run(
      DefaultLeagueServiceTest.class,
      List.of(
        new TestCase<
          Tuple2<Long, UserLeagueRequest>,
          Either<ZoneBlitzError, League>
        >(
          "invalid request",
          new Tuple2<Long, UserLeagueRequest>(
            0L,
            new UserLeagueRequest("league")
          ),
          __ -> Either.left(new ZoneBlitzValidationError("validation error")),
          (input, output) -> {
            when(validator.validateUserRequest(input._2)).thenReturn(
              Either.left(output.getLeft())
            );
          }
        ),
        new TestCase<
          Tuple2<Long, UserLeagueRequest>,
          Either<ZoneBlitzError, League>
        >(
          "valid request",
          new Tuple2<Long, UserLeagueRequest>(
            0L,
            new UserLeagueRequest("league")
          ),
          input -> Either.right(new League(0, input._1, input._2.name())),
          (input, output) -> {
            when(validator.validateUserRequest(input._2)).thenReturn(
              Either.right(input._2)
            );
            when(
              repository.insertOne(new InsertLeagueRequest(input._1, input._2))
            ).thenReturn(output);
          }
        )
      ),
      input -> service.create(input._1, input._2)
    );
  }
}
