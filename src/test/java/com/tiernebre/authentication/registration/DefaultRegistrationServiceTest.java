package com.tiernebre.authentication.registration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import org.junit.Test;

public final class DefaultRegistrationServiceTest {

  private final RegistrationRepository repository = mock(
    RegistrationRepository.class
  );
  private final PasswordHasher passwordHasher = mock(PasswordHasher.class);
  private final AccountService accountService = mock(AccountService.class);
  private final RegistrationValidator validator = mock(
    RegistrationValidator.class
  );

  private final RegistrationService service = new DefaultRegistrationService(
    repository,
    passwordHasher,
    accountService,
    validator
  );

  @Test
  public void create() {
    TestCaseRunner.run(
      DefaultRegistrationServiceTest.class,
      List.of(
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, Registration>
        >(
          "invalid request",
          new CreateRegistrationRequest(null, null, null),
          __ -> Either.left(List.of("invalid request")),
          (request, expected) -> {
            when(validator.parse(request)).thenReturn(
              Either.left(expected.getLeft())
            );
          }
        )
      ),
      service::create
    );
  }
}
