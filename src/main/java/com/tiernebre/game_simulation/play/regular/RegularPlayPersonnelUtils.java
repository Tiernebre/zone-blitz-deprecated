package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.PlayerAttributes;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayOffensivePersonnel;
import java.util.function.Function;
import java.util.stream.Stream;

public final class RegularPlayPersonnelUtils {

  public static int getAverageAttribute(
    RegularPlayOffensivePersonnel offensivePersonnel,
    Function<PlayerAttributes, Integer> toAttribute
  ) {
    return getAverageAttribute(getAllPlayers(offensivePersonnel), toAttribute);
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

  public static Player[] getAllPlayers(
    RegularPlayOffensivePersonnel offensivePersonnel
  ) {
    Player[] offensivePlayers =
      new Player[EngineConstants.NUMBER_OF_PLAYERS_ON_FIELD_PER_SIDE];
    var offensivePlayersIndex = 0;
    for (int i = 0; i < offensivePersonnel.runningBacks().length; i++) {
      offensivePlayers[offensivePlayersIndex++] =
        offensivePersonnel.runningBacks()[i];
    }
    for (int i = 0; i < offensivePersonnel.wideReceivers().length; i++) {
      offensivePlayers[offensivePlayersIndex++] =
        offensivePersonnel.wideReceivers()[i];
    }
    for (int i = 0; i < offensivePersonnel.tightEnds().length; i++) {
      offensivePlayers[offensivePlayersIndex++] =
        offensivePersonnel.tightEnds()[i];
    }
    offensivePlayers[offensivePlayersIndex++] =
      offensivePersonnel.quarterback();
    offensivePlayers[offensivePlayersIndex++] = offensivePersonnel.leftTackle();
    offensivePlayers[offensivePlayersIndex++] = offensivePersonnel.leftGuard();
    offensivePlayers[offensivePlayersIndex++] = offensivePersonnel.center();
    offensivePlayers[offensivePlayersIndex++] = offensivePersonnel.rightGuard();
    offensivePlayers[offensivePlayersIndex++] =
      offensivePersonnel.rightTackle();
    return offensivePlayers;
  }

  public static Player[] getAllOffensiveLinemen(
    RegularPlayOffensivePersonnel offensivePersonnel
  ) {
    Player[] offensiveLinemen =
      new Player[EngineConstants.NUMBER_OF_OFFENSIVE_LINEMEN];
    var i = 0;
    offensiveLinemen[i++] = offensivePersonnel.leftTackle();
    offensiveLinemen[i++] = offensivePersonnel.leftGuard();
    offensiveLinemen[i++] = offensivePersonnel.center();
    offensiveLinemen[i++] = offensivePersonnel.rightGuard();
    offensiveLinemen[i++] = offensivePersonnel.rightTackle();
    return offensiveLinemen;
  }
}
