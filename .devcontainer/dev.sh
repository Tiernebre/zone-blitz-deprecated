#!/bin/sh

set -e

while true; do
  rm -fr build
  make clean
  make install
  make start &
  trap "trap - SIGTERM && kill -- -$$" SIGINT SIGTERM EXIT
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
  kill -9 $(pgrep -f "java -classpath")
done
