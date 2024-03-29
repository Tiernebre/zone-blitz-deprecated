package com.tiernebre.web.templates;

import com.tiernebre.web.controllers.league_management.LeagueManagementWebConstants;
import com.tiernebre.web.templates.interfaces.UsesForm;
import io.jstach.jstache.JStache;

@JStache(path = "create_league")
public record CreateLeague() implements UsesForm {
  String nameFieldName() {
    return LeagueManagementWebConstants.LEAGUE_NAME_FIELD_NAME;
  }
}
