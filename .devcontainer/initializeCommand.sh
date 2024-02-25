#!/bin/sh

set -e

bold=$(tput bold)
normal=$(tput sgr0)

DEVELOPMENT_HOST=dev.zoneblitz.app
HOST_ENTRY="127.0.0.1\t${DEVELOPMENT_HOST}\n::1             ${DEVELOPMENT_HOST}\n"

if [ -n "${CI}" ]; then
  echo "Certification does not need to be created as this is being ran in a CI environment. Skipping and proceeding with dev container creation."
  exit 0
else
  .devcontainer/generateCertificates.sh
fi

if grep -Fxq "$DEVELOPMENT_HOST" /etc/hosts; then
  echo "${DEVELOPMENT_HOST} already exists in /etc/hosts. Skipping automated host resolution setup."
else
  echo "${DEVELOPMENT_HOST} does not exist in /etc/hosts. Proceeding with adding this host to your /etc/hosts file.\n"
  echo "Adding the host requires sudo permissions. You may need to provide your sudo password below.\n${bold}If you do not feel comfortable with this, you can manually add the following hosts to your /etc/hosts file:${normal}\n"
  echo "${HOST_ENTRY}"
  echo "${HOST_ENTRY}" | sudo tee -a /etc/hosts > /dev/null
  echo "Automatically added ${DEVELOPMENT_HOST} to your hosts file."
fi
