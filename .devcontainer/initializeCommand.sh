#!/bin/sh

if [ -n "${CI}" ]; then
  echo "Certification does not need to be created as this is being ran in a CI environment. Skipping and proceeding with dev container creation."
  exit 0
fi

if [ -f .devcontainer/certs/localhost.cert ]; then
  echo "Certification was already created. Skipping certification creation and proceeding with dev container creation."
  exit 0
else
  echo "Certification does not exist already for dev container. Proceeding to create certifications."
fi

source .devcontainer/generateCertificates.sh
