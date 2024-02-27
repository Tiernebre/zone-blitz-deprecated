#!/bin/sh

stop_gradle() {
  gradle --stop >> /dev/null
}

stop_gradle
while true; do
  make serve &
  PID=$!
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates >> /dev/ull
  stop_gradle
  kill -9 "${PID}" >> /dev/null
done
