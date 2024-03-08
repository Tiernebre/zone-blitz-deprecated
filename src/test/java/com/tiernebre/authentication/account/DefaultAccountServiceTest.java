package com.tiernebre.authentication.account;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.function.BiConsumer;
import org.junit.Test;

public class DefaultAccountServiceTest {

  private final AccountRepository repository = mock(AccountRepository.class);
  private final AccountService service = new DefaultAccountService(repository);

  private final record Case(
    String name,
    String googleAccountId,
    Either<String, Account> expected,
    BiConsumer<String, Either<String, Account>> mock
  ) {}

  @Test
  public void getForGoogleAccountId() {
    var cases = new Case[] {
      new Case(
        "Null Google account id",
        null,
        Either.left("Given Google account id is null."),
        null
      ),
      new Case("Existing Google account", "existing_id", Either.right(
          new Account(1L, null, "existing_id")
        ), (accountId, accountResult) -> {
          when(repository.selectOneByGoogleAccountId(accountId)).thenReturn(
            accountResult.toOption()
          );
        }),
      new Case("New Google account", "new_id", Either.right(
          new Account(1L, null, "new_id")
        ), (accountId, accountResult) -> {
          when(repository.selectOneByGoogleAccountId(accountId)).thenReturn(
            Option.none()
          );
          when(repository.insertOne(accountId, null)).thenReturn(
            accountResult.get()
          );
        }),
    };
    for (var test : cases) {
      if (test.mock() != null) {
        test.mock().accept(test.googleAccountId(), test.expected());
      }
      assertEquals(
        test.expected(),
        service.getForGoogleAccount(test.googleAccountId())
      );
    }
  }
}
