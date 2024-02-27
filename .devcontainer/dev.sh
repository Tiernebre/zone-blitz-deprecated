#!/bin/sh

make install
while true; do
  make serve &
  inotifywait -r -e modify src/main/java/com/tiernebre src/main/resources/templates
done
