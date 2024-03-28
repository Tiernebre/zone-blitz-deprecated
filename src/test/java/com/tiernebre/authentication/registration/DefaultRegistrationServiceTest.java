package com.tiernebre.authentication.registration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import com.tiernebre.util.error.ZoneBlitzClientError;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.javalin.http.HttpStatus;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class DefaultRegistrationServiceTest {

  private final RegistrationRepository repository = mock(
    RegistrationRepository.class
  );
  private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
  private final AccountService accountService = mock(AccountService.class);
  private final RegistrationValidator validator = mock(
    RegistrationValidator.class
  );

  private final RegistrationService service = new DefaultRegistrationService(
    repository,
    passwordEncoder,
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
          Either<ZoneBlitzError, Registration>
        >(
          "invalid request",
          new CreateRegistrationRequest(null, null, null),
          __ ->
            Either.left(
              new ZoneBlitzClientError(
                "invalid request",
                HttpStatus.BAD_REQUEST
              )
            ),
          (request, expected) -> {
            when(validator.parse(request)).thenReturn(
              Either.left(expected.getLeft())
            );
          }
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, Registration>
        >(
          "existing username",
          new CreateRegistrationRequest("username", "password", "password"),
          __ ->
            Either.left(
              new ZoneBlitzClientError(
                "The requested username already exists. Please specify a different username.",
                HttpStatus.CONFLICT
              )
            ),
          (request, expected) -> {
            var username = request.username();
            var password = request.password();
            when(validator.parse(request)).thenReturn(
              Either.right(new RegistrationRequest(username, password))
            );
            when(repository.selectOneByUsername(username)).thenReturn(
              Option.of(new Registration(0, username, password))
            );
          }
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, Registration>
        >(
          "persist error",
          new CreateRegistrationRequest(null, null, null),
          __ ->
            Either.left(
              new ZoneBlitzServerError(
                "Could not create registration due to an error on our end."
              )
            ),
          (request, expected) -> {
            var username = request.username();
            var password = request.password();
            var hashedPassword = "hashed";
            when(validator.parse(request)).thenReturn(
              Either.right(new RegistrationRequest(username, password))
            );
            when(repository.selectOneByUsername(username)).thenReturn(
              Option.none()
            );
            when(passwordEncoder.encode(password)).thenReturn(hashedPassword);
            when(repository.insertOne(username, hashedPassword)).thenReturn(
              expected
            );
          }
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<ZoneBlitzError, Registration>
        >(
          "happy path returns a created registration",
          new CreateRegistrationRequest("username", "password", "password"),
          request ->
            Either.right(
              new Registration(
                0,
                request.username(),
                UUID.randomUUID().toString()
              )
            ),
          (request, expected) -> {
            var username = request.username();
            var password = request.password();
            var hashedPassword = expected.get().password();
            when(validator.parse(request)).thenReturn(
              Either.right(new RegistrationRequest(username, password))
            );
            when(repository.selectOneByUsername(username)).thenReturn(
              Option.none()
            );
            when(passwordEncoder.encode(password)).thenReturn(hashedPassword);
            when(repository.insertOne(username, hashedPassword)).thenReturn(
              Either.right(expected.get())
            );
            when(accountService.create(expected.get())).thenReturn(
              new Account(0, null, null)
            );
          },
          (__, expected) -> {
            verify(accountService, times(1)).create(expected.get());
          }
        )
      ),
      service::create
    );
  }

  @Test
  public void getOne() {
    TestCaseRunner.run(
      DefaultRegistrationServiceTest.class,
      List.of(
        new TestCase<Tuple2<String, String>, Option<Registration>>(
          "no existing one by username",
          Tuple.of("username", "password"),
          __ -> Option.none(),
          (request, __) -> {
            when(repository.selectOneByUsername(request._1)).thenReturn(
              Option.none()
            );
          }
        ),
        new TestCase<Tuple2<String, String>, Option<Registration>>(
          "passwords do not match",
          Tuple.of("username", "password"),
          request -> Option.none(),
          (request, expected) -> {
            var registration = new Registration(
              1,
              request._1,
              UUID.randomUUID().toString()
            );
            when(repository.selectOneByUsername(request._1)).thenReturn(
              Option.of(registration)
            );
            when(
              passwordEncoder.matches(request._2, registration.password())
            ).thenReturn(false);
          }
        ),
        new TestCase<Tuple2<String, String>, Option<Registration>>(
          "happy path existing registration",
          Tuple.of("username", "password"),
          request ->
            Option.of(
              new Registration(1, request._1, UUID.randomUUID().toString())
            ),
          (request, expected) -> {
            var registration = expected.get();
            when(repository.selectOneByUsername(request._1)).thenReturn(
              expected
            );
            when(
              passwordEncoder.matches(request._2, registration.password())
            ).thenReturn(true);
          }
        )
      ),
      request -> service.getOne(request._1, request._2)
    );
  }
}
