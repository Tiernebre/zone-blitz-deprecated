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
	mvn clean package -DskipTests=true

.PHONY: install
install:
	npm ci

.PHONY: test
test:
	npm run lint
	mvn test
	
.PHONY: format
format:
	npm run format
