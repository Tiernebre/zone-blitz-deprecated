package com.tiernebre.web;

import com.tiernebre.web.util.SessionRegistry;
import com.tiernebre.web.util.WebHelper;

public record WebContext(SessionRegistry sessionRegistry, WebHelper helper) {}
