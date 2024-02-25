FROM alpine:3.19

RUN apk add openjdk21 fish make git openssh-client maven nodejs npm R bash inotify-tools
ADD https://dl.filippo.io/mkcert/latest?for=linux/amd64 /usr/local/bin/mkcert
RUN chmod +x /usr/local/bin/mkcert

# shell setup
SHELL ["fish", "--command"]
ENV SHELL /usr/bin/fish
ADD config.fish /root/.config/fish/config.fish

EXPOSE 8000

ENTRYPOINT [ "fish" ]
