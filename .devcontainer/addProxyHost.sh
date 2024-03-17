#!/bin/sh

echo "🌐 Adding dev.zoneblitz.app proxy route to hosts file.\n"

DOMAIN=proxy
IP=$(dig +short $DOMAIN)
HOST_ENTRY="$IP $ZONE_BLITZ_DOMAIN"
NODE_EXTRA_CA_CERTS=/usr/local/share/ca-certificates/dev.zoneblitz.app.cert

if grep -q "$ZONE_BLITZ_DOMAIN" /etc/hosts; then
  echo "$ZONE_BLITZ_DOMAIN already exists in /etc/hosts. Skipping."
else
  echo "Adding $HOST_ENTRY to /etc/hosts"
  echo "${HOST_ENTRY}" | tee -a /etc/hosts > /dev/null
  echo "Added ${HOST_ENTRY} to /etc/hosts"
fi
