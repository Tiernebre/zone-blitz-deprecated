package com.tiernebre.game_simulation.dto;

import net.datafaker.Faker;

public final class DtoMockFactory {

  private static final Faker FAKER = new Faker();

  public static Team team() {
    return new Team(
      "Chicago",
      "Blazers",
      new Player[] { player() },
      null,
      null
    );
  }

  public static Player player() {
    return player(0);
  }

  public static Player player(int attribute) {
    return new Player(
      FAKER.name().malefirstName(),
      FAKER.name().lastName(),
      0,
      playerAttributes(attribute)
    );
  }

  public static PlayerAttributes playerAttributes(int attribute) {
    return new PlayerAttributes(
      attribute,
      attribute,
      attribute,
      attribute,
      attribute,
      attribute
    );
  }
}
