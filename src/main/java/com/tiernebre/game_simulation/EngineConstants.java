package com.tiernebre.game_simulation;

public final class EngineConstants {

  public static final int MINIMUM_PLAYER_ATTRIBUTE = 0;
  public static final int AVERAGE_PLAYER_ATTRIBUTE = 50;
  public static final int MAXIMUM_PLAYER_ATTRIBUTE = 100;

  public static final int NUMBER_OF_PLAYERS_ON_FIELD_PER_SIDE = 11;
  public static final int NUMBER_OF_OFFENSIVE_LINEMEN = 5;

  public static final int FIELD_YARDS = 100;
  public static final int END_ZONE_YARD_LINE = FIELD_YARDS / 2;
  public static final int GOAL_POST_YARD_LINE = END_ZONE_YARD_LINE + 10;
  public static final int WEST_END_ZONE_YARD_LINE = -END_ZONE_YARD_LINE;
  public static final int EAST_END_ZONE_YARD_LINE = END_ZONE_YARD_LINE;
  public static final int WEST_GOAL_POST_YARD_LINE = -GOAL_POST_YARD_LINE;
  public static final int EAST_GOAL_POST_YARD_LINE = GOAL_POST_YARD_LINE;

  public static final int FIELD_GOAL_YARDS_BEHIND_LINE_ATTEMPT = 7;

  public static final int DEFAULT_YARDS_TO_GO = 10;
}
