package com.tiernebre.game_simulation.dto.personnel;

import com.tiernebre.game_simulation.dto.Player;

public record KickoffPersonnel(Player kickOffSpecialist, Player[] gunners) {}
