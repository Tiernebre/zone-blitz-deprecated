package com.tiernebre.game_simulation.dto;

public record Player(
  String firstName,
  String lastName,
  int number,
  PlayerAttributes attributes
) {
  public String toString() {
    return String.format("%s %s (#%d)", firstName, lastName, number);
  }
}
