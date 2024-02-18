FROM alpine:3.19

WORKDIR /usr/app
COPY . .

RUN apk add openjdk21 maven make npm
RUN make compile
RUN apk del npm

EXPOSE 8000

CMD [ "make", "run" ]
