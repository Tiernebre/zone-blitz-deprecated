package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.PlayerAttributes;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.playbook.offense.personnel.RegularPlayOffensivePersonnel;
import java.util.function.Function;
import java.util.stream.Stream;

public final class RegularPlayPersonnelUtils {

  public static int getAverageAttribute(
    RegularPlayOffensivePersonnel offensivePersonnel,
    Function<PlayerAttributes, Integer> toAttribute
  ) {
    return getAverageAttribute(offensivePersonnel.players(), toAttribute);
  }

  public static int getAverageAttribute(
    RegularPlayDefensivePersonnel defensivePersonnel,
    Function<PlayerAttributes, Integer> toAttribute
  ) {
    return getAverageAttribute(getAllPlayers(defensivePersonnel), toAttribute);
  }

  public static int getAverageAttribute(
    Player[] players,
    Function<PlayerAttributes, Integer> toAttribute
  ) {
    if (players.length == 0) return EngineConstants.MINIMUM_PLAYER_ATTRIBUTE;

    return (
      Stream.of(players)
        .map(
          player ->
            player == null
              ? EngineConstants.MINIMUM_PLAYER_ATTRIBUTE
              : toAttribute.apply(player.attributes())
        )
        .reduce(0, (total, attribute) -> (total + attribute)) /
      players.length
    );
  }

  public static Player[] getAllPlayers(
    RegularPlayDefensivePersonnel defensivePersonnel
  ) {
    Player[] defensivePlayers =
      new Player[EngineConstants.NUMBER_OF_PLAYERS_ON_FIELD_PER_SIDE];
    var defensivePlayersIndex = 0;
    for (int i = 0; i < defensivePersonnel.linemen().length; i++) {
      defensivePlayers[defensivePlayersIndex++] =
        defensivePersonnel.linemen()[i];
    }
    for (int i = 0; i < defensivePersonnel.linebackers().length; i++) {
      defensivePlayers[defensivePlayersIndex++] =
        defensivePersonnel.linebackers()[i];
    }
    for (int i = 0; i < defensivePersonnel.cornerbacks().length; i++) {
      defensivePlayers[defensivePlayersIndex++] =
        defensivePersonnel.cornerbacks()[i];
    }
    defensivePlayers[defensivePlayersIndex++] = defensivePersonnel.freeSafety();
    defensivePlayers[defensivePlayersIndex++] =
      defensivePersonnel.strongSafety();
    return defensivePlayers;
  }
}
