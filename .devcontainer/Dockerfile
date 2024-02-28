FROM alpine:3.19

RUN apk add openjdk21 fish make git openssh-client nodejs npm inotify-tools gradle

# needed for DevContainers CI :(
RUN apk add bash

# shell setup
SHELL ["fish", "--command"]
ENV SHELL /usr/bin/fish
ADD config.fish /root/.config/fish/config.fish

EXPOSE 8000

ENTRYPOINT [ "fish" ]
