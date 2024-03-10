FROM alpine:3.19

RUN apk add openjdk21 fish make git openssh-client nodejs npm inotify-tools gradle bash

ADD https://github.com/amacneil/dbmate/releases/latest/download/dbmate-linux-amd64 /usr/bin/dbmate
RUN chmod +x /usr/bin/dbmate

# shell setup
SHELL ["fish", "--command"]
ENV SHELL /usr/bin/fish
ADD config.fish /root/.config/fish/config.fish

# playwright setup
ENV PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
ENV PLAYWRIGHT_CHROMIUM_EXECUTABLE_PATH=/usr/bin/chromium-browser
ENV PLAYWRIGHT_NODEJS_PATH=/usr/bin/node

EXPOSE 8000

ENTRYPOINT [ "fish" ]
