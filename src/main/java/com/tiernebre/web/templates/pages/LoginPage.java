package com.tiernebre.web.templates.pages;

import io.jstach.jstache.JStache;

@JStache(path = "login")
public record LoginPage(String clientId) {}
