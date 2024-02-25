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

if ! command -v mkcert &> /dev/null
then
    echo "mkcert is required to run the zone blitz dev container. Please install using the instructions at https://github.com/FiloSottile/mkcert?tab=readme-ov-file#installation"
    exit 1
fi

echo "You _may_ need to require sudo access. This is because mkcert will trust the generated certificate for you to avoid browser issues. Please provide the sudo password, otherwise you can run mkcert manually outside of this script."

source installCertificates.sh
