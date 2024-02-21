package com.tiernebre.game_simulation.dto.game;

import com.tiernebre.game_simulation.dto.Team;

public record Game(GameState state, Team home, Team away) {}
