.PHONY: serve
serve: compile run

.PHONY: run
run:
	mvn exec:java

.PHONY: compile
compile: install build

.PHONY: build
build:
	npm run build
	mvn clean package -q -DskipTests=true

.PHONY: test
test:
	npm run lint
	mvn test

.PHONY: install
install:
	npm ci
	
.PHONY: format
format:
	npm run format

.PHONY: update
update:
	npm upgrade
	mvn versions:use-latest-versions
