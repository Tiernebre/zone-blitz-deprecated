package com.tiernebre.league_management.league;

public record CreateLeagueRequest(
  long accountId,
  UserLeagueRequest userRequest
) {}
