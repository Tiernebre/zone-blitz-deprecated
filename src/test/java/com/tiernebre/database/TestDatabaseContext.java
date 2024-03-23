package com.tiernebre.database;

import com.tiernebre.authentication.account.AccountRepository;
import org.jooq.DSLContext;

public record TestDatabaseContext(
  DSLContext dsl,
  AccountRepository accountRepository
) {}
