package com.tiernebre.web.templates;

import io.jstach.jstache.JStache;

@JStache(path = "error")
public record Error(String error) {}
