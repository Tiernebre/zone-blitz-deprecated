FROM alpine:3.19

WORKDIR /usr/app
COPY . .

RUN apk add openjdk21 fish make git openssh-client maven nodejs npm R bash inotify-tools

# shell setup
SHELL ["fish", "--command"]
ENV SHELL /usr/bin/fish
ADD config.fish /root/.config/fish/config.fish

EXPOSE 8000

ENTRYPOINT [ "fish" ]
