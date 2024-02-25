#!/bin/sh

if [ -f .devcontainer/certs/localhost.cert ]; then
  exit 0
else
  echo "Certification does not exist already for dev container. Proceeding to create certifications."
fi

if ! command -v mkcert &> /dev/null
then
    echo "mkcert is required to run the zone blitz dev container. Please install using the instructions at https://github.com/FiloSottile/mkcert?tab=readme-ov-file#installation"
    exit 1
fi


mkcert -install
mkcert -cert-file .devcontainer/certs/localhost.cert -key-file .devcontainer/certs/localhost.key localhost
