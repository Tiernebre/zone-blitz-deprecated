.PHONY: serve
serve: compile assets-build run

.PHONY: run
run:
	java -jar target/zone-blitz-1.0-SNAPSHOT.jar

.PHONY: compile
compile:
	mvn compile assembly:single -q

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

.PHONY: assets-build
assets-build:
	npm run build
	
.PHONY: fmt
fmt:
	npm run format
