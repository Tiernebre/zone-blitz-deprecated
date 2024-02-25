#!/bin/sh
#
if ! command -v mkcert &> /dev/null
then
    echo "mkcert is required to run the zone blitz dev container. Please install using the instructions at https://github.com/FiloSottile/mkcert?tab=readme-ov-file#installation"
    exit 1
fi

echo "You _may_ need to require sudo access. This is because mkcert will trust the generated certificate for you to avoid browser issues. Please provide the sudo password, otherwise you can run mkcert manually outside of this script."

mkcert -install
mkcert -cert-file .devcontainer/certs/localhost.cert -key-file .devcontainer/certs/localhost.key localhost