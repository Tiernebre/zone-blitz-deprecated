package com.tiernebre.database;

import java.util.Collection;

public record RepositoryCollection<T>(
  Collection<T> collection,
  boolean hasMore
) {}
