package com.tiernebre.league_management.league;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tiernebre.database.JooqDatabaseTest;
import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.pagination.PageRequest;
import java.util.Collections;
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
    var expected = IntStream.range(0, 2)
      .boxed()
      .map(__ -> {
        var league = dsl.newRecord(Tables.LEAGUE);
        league.setAccountId(accountId);
        league.setName(UUID.randomUUID().toString());
        league.store();
        return league.into(League.class);
      })
      .collect(Collectors.toList());
    var selected = repository.selectForAccount(
      accountId,
      new PageRequest(expected.size(), null)
    );
    assertEquals(expected, selected.edges());
  }

  @Test
  public void selectForAccountRespectsLimit() {
    var accountId = context
      .accountRepository()
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    var sliceSize = 5;
    IntStream.range(0, sliceSize + 1)
      .boxed()
      .map(__ -> {
        var league = dsl.newRecord(Tables.LEAGUE);
        league.setAccountId(accountId);
        league.setName(UUID.randomUUID().toString());
        league.store();
        return league.into(League.class);
      })
      .limit(sliceSize)
      .collect(Collectors.toList());
    var selected = repository.selectForAccount(
      accountId,
      new PageRequest(sliceSize, null)
    );
    assertEquals(sliceSize, selected.edges().size());
  }

  @Test
  public void selectForAccountHandlesCursor() {
    var accountId = context
      .accountRepository()
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    var expected = IntStream.range(0, 10)
      .boxed()
      .map(__ -> {
        var league = dsl.newRecord(Tables.LEAGUE);
        league.setAccountId(accountId);
        league.setName(UUID.randomUUID().toString());
        league.store();
        return league.into(League.class);
      })
      .collect(Collectors.toList());
    var cursor = cursorMapper.idToCursor(expected.get(3).id());
    var selected = repository.selectForAccount(
      accountId,
      new PageRequest(10, cursor)
    );
    assertEquals(6, selected.edges().size());
    assertEquals(
      expected.stream().skip(4).collect(Collectors.toList()),
      selected.edges()
    );
  }

  @Test
  public void selectForAccountThatDoesNotExist() {
    var selected = repository.selectForAccount(
      Long.MAX_VALUE,
      new PageRequest(10, null)
    );
    assertEquals(Collections.emptyList(), selected.edges());
  }
}
