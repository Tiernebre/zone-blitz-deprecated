package com.tiernebre.authentication.account;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Test;

public class DefaultAccountServiceTest {

  private final AccountRepository repository = mock(AccountRepository.class);
  private final AccountService service = new DefaultAccountService(repository);

  @Test
  public void getForGoogleAccount() {
    TestCaseRunner.<String, Either<ZoneBlitzError, Account>>run(
      DefaultAccountServiceTest.class,
      List.of(
        new TestCase<String, Either<ZoneBlitzError, Account>>(
          "Null Google account id",
          null,
          __ ->
            Either.left(
              new ZoneBlitzServerError("Given Google account id is null.")
            )
        ),
        new TestCase<String, Either<ZoneBlitzError, Account>>(
          "Existing Google Account",
          "existing_id",
          __ -> Either.right(new Account(1L, null, "existing_id")),
          (accountId, accountResult) -> {
            when(repository.selectOneByGoogleAccountId(accountId)).thenReturn(
              accountResult.toOption()
            );
          }
        ),
        new TestCase<String, Either<ZoneBlitzError, Account>>(
          "New Google account",
          "new_id",
          __ -> Either.right(new Account(1L, null, "new_id")),
          (accountId, accountResult) -> {
            when(repository.selectOneByGoogleAccountId(accountId)).thenReturn(
              Option.none()
            );
            when(repository.insertOne(accountId, null)).thenReturn(
              accountResult.get()
            );
          }
        )
      ),
      service::getForGoogleAccount
    );
  }
}
