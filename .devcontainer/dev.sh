#!/bin/sh

while true; do
  make serve &
  PID=$!
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  gradle --stop >> /dev/null
  kill -9 "${PID}" >> /dev/null
done
