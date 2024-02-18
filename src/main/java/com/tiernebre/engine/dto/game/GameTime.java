package com.tiernebre.engine.dto.game;

public record GameTime(int secondsRemaining, int totalSeconds) {
  public Quarter quarter() {
    if (secondsRemaining >= totalSeconds * 0.75) {
      return Quarter.FIRST;
    } else if (secondsRemaining >= totalSeconds * 0.5) {
      return Quarter.SECOND;
    } else if (secondsRemaining >= totalSeconds * 0.25) {
      return Quarter.THIRD;
    } else {
      return Quarter.FOURTH;
    }
  }
}
