package com.tiernebre.context;

import com.tiernebre.web.controllers.authentication.SessionRegistry;

public record WebDependencyContext(SessionRegistry sessionRegistry) {}
