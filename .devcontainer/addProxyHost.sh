#!/bin/sh

echo "Adding proxy host to /etc/hosts"
DOMAIN=$(dig +short proxy)
echo "Adding $DOMAIN"
