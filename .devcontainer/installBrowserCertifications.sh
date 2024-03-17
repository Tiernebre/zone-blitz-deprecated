#!/bin/sh

echo "🔐 Adding and trusting the local proxy self signed certificates.\n"
apk add nss-tools

CERT=.devcontainer/certs/dev.zoneblitz.app.cert

# Install to root certs
cp $CERT /usr/local/share/ca-certificates/dev.zoneblitz.app.cert

# Create nssdb directory for certutil
mkdir -p $HOME/.pki/nssdb

# Create new database
certutil -N --empty-password -d sql:$HOME/.pki/nssdb

# Add Server certificate
certutil -A \
  -n "Zone Blitz" \
  -t "TC,," \
  -d sql:$HOME/.pki/nssdb \
  -i $CERT

# Update system CA certificates
update-ca-certificates --fresh

# Create auto_select_certificate.json in the Chromium policies directory
mkdir -p /etc/chromium/policies/managed/
echo '{"AutoSelectCertificateForUrls": ["{\"pattern\":\"*\",\"filter\":{}}"]}' > /etc/chromium/policies/managed/auto_select_certificate.json
