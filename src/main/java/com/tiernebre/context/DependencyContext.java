package com.tiernebre.context;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.database.DatabaseContext;

public record DependencyContext(
  DatabaseContext database,
  AuthenticationContext authentication,
  WebDependencyContext web
) {}
