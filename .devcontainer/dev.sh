#!/bin/sh

while true; do
  make serve &
  PID=$!
  echo "${PID}"
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  gradle --stop
  kill -9 "${PID}"
done
