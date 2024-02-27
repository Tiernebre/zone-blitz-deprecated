FROM alpine:3.19

WORKDIR /usr/app
COPY . .

RUN apk add openjdk21 gradle make npm curl
RUN make install
RUN apk del npm
RUN make build

EXPOSE 8000

HEALTHCHECK --interval=5s --start-period=5s --timeout=2s --retries=5 CMD curl http://0.0.0.0:8000/api/health || exit 1

CMD [ "make", "run" ]
