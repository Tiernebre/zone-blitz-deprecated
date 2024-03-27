package com.tiernebre.context;

import com.tiernebre.authentication.AuthenticationContext;
import com.tiernebre.database.DatabaseContext;
import com.tiernebre.league_management.LeagueManagementContext;
import com.tiernebre.web.WebContext;

public record DependencyContext(
  DatabaseContext database,
  AuthenticationContext authentication,
  WebContext web,
  LeagueManagementContext leagueManagement
) {}
