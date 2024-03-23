package com.tiernebre.util.pagination;

public record PageEdge<T>(T node, String cursor) {}
