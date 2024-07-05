package com.tiernebre.game_simulation.game.play.kickoff;

import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.KickoffPersonnel;
import java.util.stream.Stream;

public final class KickoffUtils {

  public static Player[] getAllKickoffPlayers(
    KickoffPersonnel kickoffPersonnel
  ) {
    return Stream.of(
      Stream.of(kickoffPersonnel.kickOffSpecialist()),
      Stream.of(kickoffPersonnel.gunners())
    ).toArray(Player[]::new);
  }
}
