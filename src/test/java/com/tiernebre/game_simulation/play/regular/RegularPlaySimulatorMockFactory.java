package com.tiernebre.game_simulation.play.regular;

import com.tiernebre.game_simulation.EngineConstants;
import com.tiernebre.game_simulation.dto.DtoMockFactory;
import com.tiernebre.game_simulation.dto.Player;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.dto.personnel.RegularPlayOffensivePersonnel;

public final class RegularPlaySimulatorMockFactory {

  public static RegularPlaySimulatorArguments arguments() {
    return arguments(
      EngineConstants.MINIMUM_PLAYER_ATTRIBUTE,
      EngineConstants.MINIMUM_PLAYER_ATTRIBUTE
    );
  }

  public static RegularPlaySimulatorArguments arguments(
    int offensiveAttribute,
    int defensiveAttribute
  ) {
    var offensiveDecision = offensiveDecision(offensiveAttribute);
    var defensivePersonnel = defensivePersonnel(defensiveAttribute);
    return new RegularPlaySimulatorArguments(
      offensiveDecision,
      defensivePersonnel
    );
  }

  public static RegularPlayOffensiveDecision offensiveDecision(int attribute) {
    var offensivePersonnel = offensivePersonnel(attribute);
    return new RegularPlayOffensiveDecision(
      offensivePersonnel(attribute),
      offensivePersonnel.runningBacks()[0]
    );
  }

  public static RegularPlayDefensivePersonnel defensivePersonnel() {
    return defensivePersonnel(0);
  }

  public static RegularPlayDefensivePersonnel defensivePersonnel(
    int attribute
  ) {
    return new RegularPlayDefensivePersonnel(
      new Player[] {
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
      },
      new Player[] {
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
      },
      new Player[] {
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
      },
      DtoMockFactory.player(attribute),
      DtoMockFactory.player(attribute)
    );
  }

  public static RegularPlayOffensivePersonnel offensivePersonnel(
    int attribute
  ) {
    return new RegularPlayOffensivePersonnel(
      DtoMockFactory.player(attribute),
      new Player[] { DtoMockFactory.player(attribute) },
      new Player[] { DtoMockFactory.player(attribute) },
      new Player[] {
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
        DtoMockFactory.player(attribute),
      },
      DtoMockFactory.player(attribute),
      DtoMockFactory.player(attribute),
      DtoMockFactory.player(attribute),
      DtoMockFactory.player(attribute),
      DtoMockFactory.player(attribute)
    );
  }
}
