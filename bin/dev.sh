#!/bin/sh

while true
do
  make serve &
  PID=$!
  echo "Got PID=$PID"
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  echo "change happened!"
  killall make java && wait
done
