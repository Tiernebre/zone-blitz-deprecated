.PHONY: serve
serve: compile run

.PHONY: dev
dev: clean build run

.PHONY: run
run:
	java -jar target/zone-blitz-1.0-SNAPSHOT.jar

.PHONY: compile
compile: clean install build

.PHONY: build
build:
	npm run build
	mvn compile assembly:single -q

.PHONY: install
install:
	npm ci
	mvn dependency:resolve

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
