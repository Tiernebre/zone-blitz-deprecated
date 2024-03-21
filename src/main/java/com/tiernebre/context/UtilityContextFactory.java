package com.tiernebre.context;

import java.time.Clock;

public final class UtilityContextFactory {

  public UtilityContext create() {
    return new UtilityContext(Clock.systemUTC());
  }
}
