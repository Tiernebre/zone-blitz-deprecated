FROM alpine:3.19

RUN apk add openjdk21 fish make git openssh-client nodejs npm inotify-tools gradle  bash

ADD https://github.com/amacneil/dbmate/releases/latest/download/dbmate-linux-amd64 /usr/bin/dbmate
RUN chmod +x /usr/bin/dbmate

# shell setup
SHELL ["fish", "--command"]
ENV SHELL /usr/bin/fish
ADD config.fish /root/.config/fish/config.fish

EXPOSE 8000

ENTRYPOINT [ "fish" ]
