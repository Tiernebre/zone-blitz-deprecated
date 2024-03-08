package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.UUID;
import java.util.function.BiConsumer;
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

  private final record TestCase(
    String name,
    RegistrationAuthenticationRequest request,
    Either<String, Session> expected,
    BiConsumer<RegistrationAuthenticationRequest, Either<String, Session>> mock
  ) {}

  @Test
  public void authenticate() {
    var cases = new TestCase[] {
      new TestCase(
        "null request",
        null,
        Either.left("Given registration authentication request was null."),
        null
      ),
      new TestCase(
        "registration not found",
        new RegistrationAuthenticationRequest("username", "password"),
        Either.left(
          "Could not find a registration with the given username and password."
        ),
        (request, expected) -> {
          when(
            service.getOne(request.username(), request.password())
          ).thenReturn(Option.none());
        }
      ),
      new TestCase("account not found", new RegistrationAuthenticationRequest(
          "username",
          "password"
        ), Either.left(
          "Could not find an associated account for the provided registration."
        ), (request, expected) -> {
          var registration = new Registration(1, "username", "password");
          when(
            service.getOne(request.username(), request.password())
          ).thenReturn(Option.of(registration));
          when(accountService.getForRegistration(registration.id())).thenReturn(
            Option.none()
          );
        }),
      new TestCase(
        "happy path created session for a valid registration",
        new RegistrationAuthenticationRequest("username", "password"),
        Either.right(new Session(UUID.randomUUID(), 1)),
        (request, expected) -> {
          var registration = new Registration(1, "username", "password");
          var account = new Account(1, registration.id(), null);
          when(
            service.getOne(request.username(), request.password())
          ).thenReturn(Option.of(registration));
          when(accountService.getForRegistration(registration.id())).thenReturn(
            Option.of(account)
          );
          when(sessionService.create(account)).thenReturn(expected.get());
        }
      ),
    };
    for (var testCase : cases) {
      if (testCase.mock() != null) {
        testCase.mock().accept(testCase.request(), testCase.expected());
      }
      assertEquals(
        testCase.expected(),
        strategy.authenticate(testCase.request())
      );
    }
  }
}
