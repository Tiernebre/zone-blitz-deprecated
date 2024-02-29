FROM alpine:3.19 AS build
WORKDIR /usr/app
COPY . .
ENV JAVA_TOOL_OPTIONS="-Dorg.gradle.native=false"
RUN apk add openjdk21 gradle make npm curl
RUN make build

FROM alpine:3.19
RUN apk add openjdk21 curl
COPY --from=build /usr/app/build/install/zone-blitz .
EXPOSE 8000
HEALTHCHECK --interval=2s --timeout=2s --retries=5 CMD curl http://0.0.0.0:8000/api/health || exit 1
CMD [ "zone-blitz" ]
