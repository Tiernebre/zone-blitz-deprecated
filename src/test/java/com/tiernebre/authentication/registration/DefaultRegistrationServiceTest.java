package com.tiernebre.authentication.registration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tiernebre.authentication.account.Account;
import com.tiernebre.authentication.account.AccountService;
import com.tiernebre.test.TestCase;
import com.tiernebre.test.TestCaseRunner;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.UUID;
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
        ),
        new TestCase<
          CreateRegistrationRequest,
          Either<Seq<String>, Registration>
        >(
          "happy path returns a created registration",
          new CreateRegistrationRequest("username", "password", "password"),
          request ->
            Either.right(
              new Registration(0, request.username(), request.password())
            ),
          (request, expected) -> {
            var username = request.username();
            var password = request.password();
            var hashedPassword = UUID.randomUUID().toString();
            when(validator.parse(request)).thenReturn(
              Either.right(new RegistrationRequest(username, password))
            );
            when(passwordHasher.hash(password)).thenReturn(hashedPassword);
            when(repository.insertOne(username, hashedPassword)).thenReturn(
              expected.get()
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
              "hashed password"
            );
            when(repository.selectOneByUsername(request._1)).thenReturn(
              Option.of(registration)
            );
            when(
              passwordHasher.verify(request._2, registration.password())
            ).thenReturn(false);
          }
        ),
        new TestCase<Tuple2<String, String>, Option<Registration>>(
          "happy path existing registration",
          Tuple.of("username", "password"),
          request ->
            Option.of(new Registration(1, request._1, "hashed password")),
          (request, expected) -> {
            var registration = expected.get();
            var hashedPassword = "hashed password";
            when(repository.selectOneByUsername(request._1)).thenReturn(
              Option.of(
                new Registration(
                  registration.id(),
                  request._1,
                  "hashed password"
                )
              )
            );
            when(passwordHasher.verify(request._2, hashedPassword)).thenReturn(
              true
            );
          }
        )
      ),
      request -> service.getOne(request._1, request._2)
    );
  }
}