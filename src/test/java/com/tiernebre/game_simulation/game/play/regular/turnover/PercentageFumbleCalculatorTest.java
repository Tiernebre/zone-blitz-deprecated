package com.tiernebre.game_simulation.game.play.regular.turnover;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tiernebre.game_simulation.dto.DtoMockFactory;
import com.tiernebre.game_simulation.game.play.regular.RegularPlaySimulatorMockFactory;
import com.tiernebre.game_simulation.game.play.regular.turnover.PercentageFumbleCalculator;
import com.tiernebre.game_simulation.util.PercentageCalculator;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class PercentageFumbleCalculatorTest {

  @Test
  public void returnsTrueForFumble() {
    long seedForTrue = 5120;
    assertTrue(
      new PercentageFumbleCalculator(
        new PercentageCalculator(new Random(seedForTrue))
      ).occurred(
        DtoMockFactory.player(),
        RegularPlaySimulatorMockFactory.defensivePersonnel()
      )
    );
  }

  @Test
  public void returnsFalseForFumble() {
    long seedForFalse = 0;
    assertFalse(
      new PercentageFumbleCalculator(
        new PercentageCalculator(new Random(seedForFalse))
      ).occurred(
        DtoMockFactory.player(),
        RegularPlaySimulatorMockFactory.defensivePersonnel()
      )
    );
  }

  @Test
  public void accuratelyDeterminesFumbles() {
    var fumbles = 0;
    var rolls = 999999;
    var calculator = new PercentageFumbleCalculator(
      new PercentageCalculator(new Random())
    );
    var carrier = DtoMockFactory.player();
    var defensivePersonnel =
      RegularPlaySimulatorMockFactory.defensivePersonnel();
    for (int i = 0; i < rolls; i++) {
      if (calculator.occurred(carrier, defensivePersonnel)) {
        fumbles++;
      }
    }
    var rate = (double) fumbles / rolls;
    assertTrue(rate >= 0.01);
    assertTrue(rate <= 0.03);
  }
}
