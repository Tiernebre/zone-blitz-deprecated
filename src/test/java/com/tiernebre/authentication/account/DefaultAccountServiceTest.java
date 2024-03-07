package com.tiernebre.authentication.account;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import io.vavr.control.Either;
import java.util.function.Consumer;
import org.junit.Test;

public class DefaultAccountServiceTest {

  private final AccountRepository repository = mock(AccountRepository.class);
  private final AccountService service = new DefaultAccountService(repository);

  private final record Case(
    String name,
    String googleAccountId,
    Either<String, Account> expected,
    Consumer<String> mock
  ) {}

  @Test
  public void cases() {
    var cases = new Case[] {
      new Case(
        "Null Google account id",
        null,
        Either.left("Given Google account id is null."),
        null
      ),
    };
    for (var test : cases) {
      if (test.mock() != null) {
        test.mock().accept(test.googleAccountId());
      }
      assertEquals(
        test.expected(),
        service.getForGoogleAccountId(test.googleAccountId())
      );
    }
  }
}
