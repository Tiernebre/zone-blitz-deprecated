#!/bin/sh

while true; do
  make &
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  kill -9 $(pgrep -f "java -classpath")
done
