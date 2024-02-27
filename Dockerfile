FROM alpine:3.19 AS build

COPY . .

ENV JAVA_TOOL_OPTIONS="-Dorg.gradle.native=false"

RUN apk add openjdk21 gradle make npm curl 7zip
RUN make
RUN 7z x -odistribution build/distributions/zone-blitz.zip

FROM alpine:3.19
RUN apk add openjdk21 curl
COPY --from=build distribution/zone-blitz .

EXPOSE 8000
HEALTHCHECK --interval=5s --start-period=5s --timeout=2s --retries=5 CMD curl http://0.0.0.0:8000/api/health || exit 1

CMD [ "zone-blitz" ]
