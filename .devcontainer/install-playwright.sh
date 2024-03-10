#!/bin/sh

echo "https://dl-cdn.alpinelinux.org/alpine/edge/main" > /etc/apk/repositories \
  && echo "https://dl-cdn.alpinelinux.org/alpine/edge/community" >> /etc/apk/repositories \
  && echo "https://dl-cdn.alpinelinux.org/alpine/edge/testing" >> /etc/apk/repositories \
  && echo "https://dl-cdn.alpinelinux.org/alpine/v3.16/main" >> /etc/apk/repositories \
  && echo "https://dl-cdn.alpinelinux.org/alpine/v3.16/community" \
  && apk upgrade -U -a \
  && apk add \
    libstdc++ \
    chromium \
    harfbuzz \
    nss \
    freetype \
    ttf-freefont \
    font-noto-emoji \
    wqy-zenhei \
    nodejs \
  && rm -rf /var/cache/* \
  && mkdir /var/cache/apk
