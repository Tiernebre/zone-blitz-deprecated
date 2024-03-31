package com.tiernebre.league_management.league;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tiernebre.database.JooqDatabaseTest;
import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.Page;
import com.tiernebre.util.pagination.PageEdge;
import com.tiernebre.util.pagination.PageInfo;
import com.tiernebre.util.pagination.PageRequest;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public final class JooqLeagueRepositoryTest extends JooqDatabaseTest {

  private final LeagueRepository repository = new JooqLeagueRepository(
    dsl,
    paginationStrategy
  );

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
      inserted.get().id(),
      request.accountId(),
      request.userRequest().name()
    );
    assertEquals(expected, inserted);
  }

  @Test
  public void selectForAccountThatDoesNotExist() {
    var selected = repository.selectForAccount(
      Long.MAX_VALUE,
      new PageRequest(10, null)
    );
    assertEquals(Collections.emptyList(), selected.edges());
  }

  @Test
  public void selectForAccountOnlyReturnsResultsForGivenAccount() {
    var accountId = context
      .accountRepository()
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    var leagues = seedLeagues(2, accountId);
    var otherAccountId = context
      .accountRepository()
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    seedLeagues(2, otherAccountId);
    var expected = new Page<League>(
      leagues,
      new PageInfo(leagues.getLast().cursor(), false)
    );
    var selected = repository.selectForAccount(
      accountId,
      new PageRequest(leagues.size(), null)
    );
    assertEquals(expected, selected);
  }

  @Test
  public void selectOneForAccountReturnsValid() {
    var accountId = context
      .accountRepository()
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    var existingLeague = seedLeague(accountId);
    var selectedLeague = repository.selectOneForAccount(
      existingLeague.id(),
      accountId
    );
    assertTrue(selectedLeague.isDefined());
    assertEquals(existingLeague, selectedLeague.get());
  }

  private List<PageEdge<League>> seedLeagues(int size, long accountId) {
    return IntStream.range(0, size)
      .boxed()
      .map(__ -> {
        var league = seedLeague(accountId);
        return new PageEdge<League>(league, cursorMapper.toCursor(league));
      })
      .collect(Collectors.toUnmodifiableList());
  }

  private League seedLeague(long accountId) {
    var league = dsl.newRecord(Tables.LEAGUE);
    league.setAccountId(accountId);
    league.setName(UUID.randomUUID().toString());
    league.store();
    return league.into(League.class);
  }
}
