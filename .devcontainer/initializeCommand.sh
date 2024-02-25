#!/bin/sh

bold=$(tput bold)
normal=$(tput sgr0)

DOMAIN=dev.zoneblitz.app
CERT_PATH=".devcontainer/certs/${DOMAIN}"
CERT_FILE="${CERT_PATH}.cert"
KEY_FILE="${CERT_PATH}.key"
HOST_ENTRY="127.0.0.1\t${DOMAIN}\n::1             ${DOMAIN}\n"

if [ -f "${CERT_FILE}" ]; then
  echo "Certification was already created. Skipping certification creation and proceeding with dev container creation."
else
  echo "Certification does not exist already for dev container. Proceeding to create certifications."
  echo "You _may_ need to require sudo access. This is because mkcert will trust the generated certificate for you to avoid browser issues.\nPlease provide the sudo password, ${bold}otherwise you can run mkcert manually outside of this script.${normal}"

  mkcert -install
  mkcert -cert-file "${CERT_FILE}" -key-file "${KEY_FILE}" "${DOMAIN}"
fi

if grep -q "$DOMAIN" /etc/hosts; then
  echo "${DOMAIN} already exists in /etc/hosts. Skipping automated host resolution setup."
else
  echo "${DOMAIN} does not exist in /etc/hosts. Proceeding with adding this host to your /etc/hosts file.\n"
  echo "Adding the host requires sudo permissions. You may need to provide your sudo password below.\n${bold}If you do not feel comfortable with this, you can manually add the following hosts to your /etc/hosts file:${normal}\n"
  echo "${HOST_ENTRY}"
  echo "${HOST_ENTRY}" | sudo tee -a /etc/hosts > /dev/null
  echo "Automatically added ${DOMAIN} to your hosts file."
fi
