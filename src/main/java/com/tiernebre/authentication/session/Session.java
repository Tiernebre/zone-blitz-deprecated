package com.tiernebre.authentication.session;

import java.time.LocalDateTime;
import java.util.UUID;

public record Session(UUID id, long accountId, LocalDateTime expiresAt) {}
