package com.tiernebre.game_simulation.playbook.personnel;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.PlayerAttributes;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class RegularPlayPersonnel {

  public abstract int averageAttribute(
    Function<PlayerAttributes, Integer> toAttribute
  );

  public abstract Player[] players();

  protected int averageAttribute(
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
}
