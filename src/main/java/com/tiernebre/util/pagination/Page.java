package com.tiernebre.util.pagination;

import java.util.Collection;

public record Page<T>(Collection<PageEdge<T>> edges) {}
