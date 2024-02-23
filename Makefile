.PHONY: serve
serve: compile run

.PHONY: dev
dev: clean build run

.PHONY: run
run:
	mvn exec:java

.PHONY: compile
compile: clean install build

.PHONY: build
build:
	npm run build
	mvn compile

.PHONY: install
install:
	npm ci

.PHONY: test
test:
	npm run lint
	mvn test
	
.PHONY: clean
clean:
	mvn clean -q

.PHONY: format
format:
	npm run format
