#!/bin/sh

while true; do
  make &
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates package.json package-lock.json build.gradle
  kill -9 $(pgrep -f "java -classpath")
  wait
done
