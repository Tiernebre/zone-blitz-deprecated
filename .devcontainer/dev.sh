#!/bin/sh

while true; do
  make &
  PID=$!
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates >> /dev/ull
  kill -9 "${PID}" >> /dev/null
done
