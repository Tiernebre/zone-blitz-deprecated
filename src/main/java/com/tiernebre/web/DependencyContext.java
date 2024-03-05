package com.tiernebre.web;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.database.DatabaseContext;

public record DependencyContext(
  DatabaseContext database,
  AuthenticationContext authentication
) {}
