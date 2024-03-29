package com.tiernebre.web.templates.interfaces;

public interface UsesForm {
  default String error() {
    return null;
  }

  default String warning() {
    return null;
  }
}
