package com.tiernebre.authentication.registration;

import com.tiernebre.database.jooq.Tables;
import io.vavr.control.Option;
import org.jooq.DSLContext;

public final class JooqRegistrationRepository
  implements RegistrationRepository {

  private final DSLContext dsl;

  public JooqRegistrationRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public Registration insertOne(String username, byte[] password) {
    return dsl
      .insertInto(
        Tables.REGISTRATION,
        Tables.REGISTRATION.USERNAME,
        Tables.REGISTRATION.PASSWORD
      )
      .values(username, password)
      .returning()
      .fetchSingleInto(Registration.class);
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
