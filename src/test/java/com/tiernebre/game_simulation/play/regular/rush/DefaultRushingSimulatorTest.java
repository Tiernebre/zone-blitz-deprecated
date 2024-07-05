package com.tiernebre.game_simulation.play.regular.rush;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tiernebre.game_simulation.game.play.regular.defense.TackledByCalculator;
import com.tiernebre.game_simulation.game.play.regular.rush.DefaultRushingSimulator;
import com.tiernebre.game_simulation.game.play.regular.rush.RushResult;
import com.tiernebre.game_simulation.game.play.regular.rush.RushingYardageCalculator;
import com.tiernebre.game_simulation.game.play.regular.turnover.FumbleCalculator;
import com.tiernebre.game_simulation.game.play.regular.turnover.FumbleResult;
import com.tiernebre.game_simulation.personnel.defense.RegularPlayDefensivePersonnel;
import com.tiernebre.game_simulation.personnel.offense.RegularPlayOffensivePersonnel;
import com.tiernebre.game_simulation.play.regular.RegularPlaySimulatorMockFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DefaultRushingSimulatorTest {

  RushingYardageCalculator rushingYardageCalculator = Mockito.mock(
    RushingYardageCalculator.class
  );
  FumbleCalculator fumbleCalculator = Mockito.mock(FumbleCalculator.class);
  TackledByCalculator tackledByCalculator = Mockito.mock(
    TackledByCalculator.class
  );

  @Test
  public void returnsAFumbleResultOnFumbles() {
    var arguments = RegularPlaySimulatorMockFactory.arguments();
    when(fumbleCalculator.occurred(any(), any())).thenReturn(true);
    var tackledBy = arguments.offensiveDecision().personnel().center();
    when(
      tackledByCalculator.tackledBy(any(RegularPlayOffensivePersonnel.class))
    ).thenReturn(tackledBy);
    DefaultRushingSimulator simulator = new DefaultRushingSimulator(
      rushingYardageCalculator,
      fumbleCalculator,
      tackledByCalculator
    );
    var result = simulator.simulate(arguments);
    assertTrue(result instanceof FumbleResult);
    var fumbleResult = (FumbleResult) result;
    assertEquals(
      arguments.offensiveDecision().target(),
      fumbleResult.committedBy()
    );
    assertEquals(tackledBy, fumbleResult.tackledBy());
  }

  @Test
  public void returnsARushingResult() {
    when(fumbleCalculator.occurred(any(), any())).thenReturn(false);
    int yards = 8;
    when(rushingYardageCalculator.calculate(any())).thenReturn(yards);
    var arguments = RegularPlaySimulatorMockFactory.arguments();
    var tackledBy = arguments.defensivePersonnel().freeSafety();
    when(
      tackledByCalculator.tackledBy(any(RegularPlayDefensivePersonnel.class))
    ).thenReturn(tackledBy);
    DefaultRushingSimulator simulator = new DefaultRushingSimulator(
      rushingYardageCalculator,
      fumbleCalculator,
      tackledByCalculator
    );
    var result = simulator.simulate(arguments);
    assertTrue(result instanceof RushResult);
    var rushingResult = (RushResult) result;
    assertEquals(
      arguments.offensiveDecision().target(),
      rushingResult.carrier()
    );
    assertEquals(yards, rushingResult.yards());
    assertEquals(tackledBy, rushingResult.tackledBy());
  }
}
