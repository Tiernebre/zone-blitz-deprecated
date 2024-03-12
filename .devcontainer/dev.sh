#!/bin/sh

while true; do
  make clean
  make install
  make start &
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  kill -9 $(pgrep -f "java -classpath")
done
