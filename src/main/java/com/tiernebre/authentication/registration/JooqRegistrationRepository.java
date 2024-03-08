package com.tiernebre.authentication.registration;

import com.tiernebre.database.jooq.Tables;
import org.jooq.DSLContext;

public final class JooqRegistrationRepository
  implements RegistrationRepository {

  private final DSLContext dsl;

  public JooqRegistrationRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public Registration insertOne(String username, String password) {
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
}
