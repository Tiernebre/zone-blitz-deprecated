package com.tiernebre.web;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Map;
import java.util.stream.Collectors;

public final class UrlEncodedFormBodyPublisher {

  public static BodyPublisher ofParams(Map<String, Object> params) {
    String encodedForm = params
      .entrySet()
      .stream()
      .map(
        entry ->
          String.format("%s=%s", entry.getKey(), entry.getValue().toString())
      )
      .collect(Collectors.joining("&"));
    return BodyPublishers.ofString(encodedForm);
  }
}
