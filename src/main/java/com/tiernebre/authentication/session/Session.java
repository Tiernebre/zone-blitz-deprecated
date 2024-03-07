package com.tiernebre.authentication.session;

import java.util.UUID;

public record Session(UUID id, long accountId) {}
