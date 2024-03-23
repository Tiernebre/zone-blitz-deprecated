package com.tiernebre.league_management.league;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tiernebre.authentication.account.AccountRepository;
import com.tiernebre.authentication.account.JooqAccountRepository;
import com.tiernebre.database.TestJooqDslContextFactory;
import java.util.UUID;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;

public final class JooqLeagueRepositoryTest {

  private final DSLContext dsl =
    TestJooqDslContextFactory.createTestDSLContext();
  private final LeagueRepository repository = new JooqLeagueRepository(
    TestJooqDslContextFactory.createTestDSLContext()
  );

  private final AccountRepository accountRepository = new JooqAccountRepository(
    dsl
  );

  @Test
  public void insertOne() {
    var accountId = accountRepository
      .insertOne(UUID.randomUUID().toString(), null)
      .id();
    InsertLeagueRequest request = new InsertLeagueRequest(
      accountId,
      new UserLeagueRequest(UUID.randomUUID().toString())
    );
    var inserted = repository.insertOne(request);
    assertNotNull(inserted);
  }
}
