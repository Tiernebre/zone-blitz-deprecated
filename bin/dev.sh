#!/bin/sh

while true
do
  make &
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  killall make
  kill $(pgrep -f "java -jar")
done
