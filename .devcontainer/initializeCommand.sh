#!/bin/sh

if [ -n "${CI}" ]; then
  echo "Certification does not need to be created as this is being ran in a CI environment. Skipping and proceeding with dev container creation."
  exit 0
else
  source .devcontainer/generateCertificates.sh
fi

