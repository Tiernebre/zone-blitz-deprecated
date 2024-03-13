package com.tiernebre.authentication.registration;

import com.tiernebre.database.jooq.Tables;
import com.tiernebre.util.error.ZoneBlitzError;
import com.tiernebre.util.error.ZoneBlitzServerError;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.jooq.DSLContext;

public final class JooqRegistrationRepository
  implements RegistrationRepository {

  private final DSLContext dsl;

  public JooqRegistrationRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public Either<ZoneBlitzError, Registration> insertOne(
    String username,
    byte[] password
  ) {
    return Try.of(
      () ->
        dsl
          .insertInto(
            Tables.REGISTRATION,
            Tables.REGISTRATION.USERNAME,
            Tables.REGISTRATION.PASSWORD
          )
          .values(username, password)
          .returning()
          .fetchSingleInto(Registration.class)
    )
      .toEither()
      .mapLeft(exception -> new ZoneBlitzServerError(exception.getMessage()));
  }

  @Override
  public Option<Registration> selectOneByUsername(String username) {
    return Option.of(
      dsl.fetchOne(
        Tables.REGISTRATION,
        Tables.REGISTRATION.USERNAME.eq(username)
      )
    ).map(result -> result.into(Registration.class));
  }
}
