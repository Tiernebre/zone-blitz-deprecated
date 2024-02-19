#!/bin/sh

make install

while true
do
  make dev &
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  killall make
  kill $(pgrep -f "java -jar")
done
