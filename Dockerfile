FROM alpine:3.19

COPY . .

RUN apk add openjdk21 maven make
RUN make compile

EXPOSE 8000

CMD [ "make", "run" ]
