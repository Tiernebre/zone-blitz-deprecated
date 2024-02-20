package com.tiernebre.game_simulation.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.Test;

public class PercentageCalculatorTest {

  @Test
  public void returnsTrueForOccurrence() {
    long seedForTrue = 4096;
    assertTrue(
      new PercentageCalculator(new Random(seedForTrue)).calculate(0.5)
    );
  }

  @Test
  public void returnsFalseForNonOccurrence() {
    long seedForFalse = 1;
    assertFalse(
      new PercentageCalculator(new Random(seedForFalse)).calculate(0.5)
    );
  }

  @Test
  public void alwaysReturnsTrueForHundredPercent() {
    for (int i = 0; i < 1000; i++) {
      assertTrue(new PercentageCalculator(new Random()).calculate(1));
    }
  }

  @Test
  public void alwaysReturnsFalseForZeroPercent() {
    for (int i = 0; i < 1000; i++) {
      assertFalse(new PercentageCalculator(new Random()).calculate(0));
    }
  }

  @Test
  public void accuratelyDeterminesOccurrences() {
    var trues = 0;
    var rolls = 999999;
    var calculator = (new PercentageCalculator(new Random()));
    double percentage = 0.5;
    for (int i = 0; i < rolls; i++) {
      if (calculator.calculate(percentage)) {
        trues++;
      }
    }
    var rate = (double) trues / rolls;
    assertTrue(rate >= 0.49);
    assertTrue(rate <= 0.51);
  }
}
