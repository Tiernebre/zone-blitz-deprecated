#!/bin/sh

mkcert -install
mkcert -cert-file .devcontainer/certs/localhost.cert -key-file .devcontainer/certs/localhost.key localhost
