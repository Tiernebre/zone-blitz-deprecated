package com.tiernebre.authentication.registration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.session.Session;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.function.BiConsumer;
import org.junit.Test;

public final class RegistrationAuthenticationStrategyTest {

  private final RegistrationService service = mock(RegistrationService.class);
  private final RegistrationAuthenticationStrategy strategy =
    new RegistrationAuthenticationStrategy(service);

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
