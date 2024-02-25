#!/bin/sh

mkcert -install
mkcert -cert-file .devcontainer/certs/localhost.pem -key-file .devcontainer/certs/localhost.pem localhost
