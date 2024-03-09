package com.tiernebre.authentication.registration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
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
        ),
        new TestCase<
          RegistrationAuthenticationRequest,
          Either<String, Session>
        >(
          "registration not found",
          new RegistrationAuthenticationRequest("username", "password"),
          __ ->
            Either.left(
              "Could not find a registration with the given username and password."
            ),
          (request, expected) -> {
            when(
              service.getOne(request.username(), request.password())
            ).thenReturn(Option.none());
          }
        ),
        new TestCase<
          RegistrationAuthenticationRequest,
          Either<String, Session>
        >(
          "account not found",
          new RegistrationAuthenticationRequest("username", "password"),
          __ ->
            Either.left(
              "Could not find an associated account for the provided registration."
            ),
          (request, expected) -> {
            var registration = new Registration(1, "username", "password");
            when(
              service.getOne(request.username(), request.password())
            ).thenReturn(Option.of(registration));
            when(
              accountService.getForRegistration(registration.id())
            ).thenReturn(Option.none());
          }
        )
      ),
      strategy::authenticate
    );
  }
}
