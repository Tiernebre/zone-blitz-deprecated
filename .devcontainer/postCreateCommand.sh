#!/bin/sh

mkcert -install
mkcert -cert-file .devcontainer/certs/local-cert.pem -key-file .devcontainer/certs/local-key.pem 0.0.0.0
