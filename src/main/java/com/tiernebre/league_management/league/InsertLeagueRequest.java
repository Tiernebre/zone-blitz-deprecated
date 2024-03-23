package com.tiernebre.league_management.league;

public record InsertLeagueRequest(
  long accountId,
  UserLeagueRequest userRequest
) {}
