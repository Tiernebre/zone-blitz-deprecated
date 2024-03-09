package com.tiernebre.authentication.registration;

import static org.mockito.Mockito.mock;

import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import io.vavr.collection.List;
import io.vavr.control.Either;
import org.junit.Test;

public final class RegistrationAuthenticationStrategyTest {

  private final RegistrationService service = mock(RegistrationService.class);
  private final AccountService accountService = mock(AccountService.class);
  private final SessionService sessionService = mock(SessionService.class);
  private final RegistrationAuthenticationStrategy strategy =
    new RegistrationAuthenticationStrategy(
      service,
      accountService,
      sessionService
    );

  @Test
  public void authenticate() {
    TestCaseRunner.run(
      RegistrationAuthenticationStrategyTest.class,
      List.of(
        new TestCase<
          RegistrationAuthenticationRequest,
          Either<String, Session>
        >(
          "null request",
          null,
          __ ->
            Either.left("Given registration authentication request was null.")
        )
      ),
      strategy::authenticate
    );
  }
}
