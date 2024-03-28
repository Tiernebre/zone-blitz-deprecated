package com.tiernebre.authentication.registration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.authentication.session.Session;
import com.tiernebre.authentication.session.SessionService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzClientError;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.javalin.http.HttpStatus;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

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
          Either<ZoneBlitzError, Session>
        >(
          "null request",
          null,
          __ ->
            Either.left(
              new ZoneBlitzClientError(
                "Given registration authentication request was null.",
                HttpStatus.BAD_REQUEST
              )
            )
        ),
        new TestCase<
          RegistrationAuthenticationRequest,
          Either<ZoneBlitzError, Session>
        >(
          "registration not found",
          new RegistrationAuthenticationRequest("username", "password"),
          __ ->
            Either.left(
              new ZoneBlitzClientError(
                "Could not find a registration with the given username and password.",
                HttpStatus.NOT_FOUND
              )
            ),
          (request, expected) -> {
            when(
              service.getOne(request.username(), request.password())
            ).thenReturn(Option.none());
          }
        ),
        new TestCase<
          RegistrationAuthenticationRequest,
          Either<ZoneBlitzError, Session>
        >(
          "account not found",
          new RegistrationAuthenticationRequest("username", "password"),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Could not find an associated account for the provided registration."
              )
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
        ),
        new TestCase<
          RegistrationAuthenticationRequest,
          Either<ZoneBlitzError, Session>
        >(
          "happy path created session for a valid registration",
          new RegistrationAuthenticationRequest("username", "password"),
          __ ->
            Either.right(
              new Session(UUID.randomUUID(), 1, LocalDateTime.now(), false)
            ),
          (request, expected) -> {
            var registration = new Registration(1, "username", "password");
            var account = new Account(1, registration.id(), null);
            when(
              service.getOne(request.username(), request.password())
            ).thenReturn(Option.of(registration));
            when(
              accountService.getForRegistration(registration.id())
            ).thenReturn(Option.of(account));
            when(sessionService.create(account)).thenReturn(expected.get());
          }
        )
      ),
      strategy::authenticate
    );
  }
}
