#!/bin/sh

set -e

if [ ! -f .env ]; then
  export $(cat .env | xargs)
fi

if ! command -v dbmate &> /dev/null; then
  sudo curl -fsSL -o /usr/bin/dbmate https://github.com/amacneil/dbmate/releases/latest/download/dbmate-linux-amd64
  sudo chmod +x /usr/bin/dbmate
fi

docker compose pull
docker compose up -d --wait
make migrate
docker system prune --all --force
