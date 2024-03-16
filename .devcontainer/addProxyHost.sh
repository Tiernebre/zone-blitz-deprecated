#!/bin/sh

DOMAIN=proxy
IP=$(dig +short $DOMAIN)
HOST_ENTRY="$IP $DOMAIN"

if grep -q "$DOMAIN" /etc/hosts; then
  echo "${DOMAIN} already exists in /etc/hosts. Skipping."
else
  echo "Adding $HOST_ENTRY to /etc/hosts"
  echo "${HOST_ENTRY}" | tee -a /etc/hosts > /dev/null
  echo "Added ${HOST_ENTRY} to /etc/hosts"
fi
