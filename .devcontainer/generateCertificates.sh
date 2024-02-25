#!/bin/sh

bold=$(tput bold)
normal=$(tput sgr0)

DOMAIN=dev.zoneblitz.app
CERT_PATH=".devcontainer/certs/${DOMAIN}"
CERT_FILE="${CERT_PATH}.cert"
KEY_FILE="${CERT_PATH}.key"

if [ -f "${CERT_FILE}" ]; then
  echo "Certification was already created. Skipping certification creation and proceeding with dev container creation."
  exit 0
else
  echo "Certification does not exist already for dev container. Proceeding to create certifications."
fi

if [ -z "${CI}" ] && ! command -v mkcert &> /dev/null; then
  echo "mkcert is required to run the zone blitz dev container. Please install using the instructions at https://github.com/FiloSottile/mkcert?tab=readme-ov-file#installation"
  exit 1
fi

echo "You _may_ need to require sudo access. This is because mkcert will trust the generated certificate for you to avoid browser issues.\nPlease provide the sudo password, ${bold}otherwise you can run mkcert manually outside of this script.${normal}"

mkcert -install
mkcert -cert-file "${CERT_FILE}" -key-file "${KEY_FILE}" "${DOMAIN}"
