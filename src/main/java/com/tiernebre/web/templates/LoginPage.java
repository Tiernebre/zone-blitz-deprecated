package com.tiernebre.web.templates;

import io.jstach.jstache.JStache;

@JStache(path = "login_page")
public record LoginPage(String clientId, String loginUrl) {}
