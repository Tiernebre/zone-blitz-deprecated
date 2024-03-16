#!/bin/sh

DOMAIN=proxy
IP=$(dig +short $DOMAIN)
HOST_ENTRY="$IP $ZONE_BLITZ_DOMAIN"

if grep -q "$ZONE_BLITZ_DOMAIN" /etc/hosts; then
  echo "$ZONE_BLITZ_DOMAIN already exists in /etc/hosts. Skipping."
else
  echo "Adding $HOST_ENTRY to /etc/hosts"
  echo "${HOST_ENTRY}" | tee -a /etc/hosts > /dev/null
  echo "Added ${HOST_ENTRY} to /etc/hosts"
fi

echo "Adding certification to trusted store."
cp .devcontainer/certs/dev.zoneblitz.app.cert /usr/local/share/ca-certificates/dev.zoneblitz.app.cert
update-ca-certificates
