package com.tiernebre.league_management.league;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tiernebre.database.JooqDatabaseTest;
import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.PageRequest;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public final class JooqLeagueRepositoryTest extends JooqDatabaseTest {

  private final LeagueRepository repository = new JooqLeagueRepository(dsl);

  @Test
  public void insertOne() {
    var accountId = context
      .accountRepository()
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    InsertLeagueRequest request = new InsertLeagueRequest(
      accountId,
      new UserLeagueRequest(UUID.randomUUID().toString())
    );
    var inserted = repository.insertOne(request);
    assertNotNull(inserted);
    var expected = new League(
      inserted.id(),
      request.accountId(),
      request.userRequest().name()
    );
    assertEquals(expected, inserted);
  }

  @Test
  public void selectForAccount() {
    var accountId = context
      .accountRepository()
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    var leagues = IntStream.range(0, 2)
      .boxed()
      .map(__ -> {
        var league = dsl.newRecord(Tables.LEAGUE);
        league.setAccountId(accountId);
        league.setName(UUID.randomUUID().toString());
        league.store();
        return league;
      });
    var selected = repository.selectForAccount(
      accountId,
      new PageRequest(2, null)
    );
    var expected = leagues.map(league -> league.into(League.class));
    assertEquals(expected, selected);
  }
}
