FROM alpine:3.19

COPY . .

RUN apk add openjdk21 maven make npm
RUN make compile
RUN apk remove make npm

EXPOSE 8000

CMD [ "make", "run" ]
