#!/bin/sh

set -e

while true; do
  trap 'kill -9 $(pgrep -f "java -classpath")' SIGINT SIGTERM EXIT
  rm -fr build
  make clean
  make install
  make start &
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  kill -9 $(pgrep -f "java -classpath")
done
