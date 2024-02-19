.PHONY: serve
serve: compile run

.PHONY: run
run:
	java -jar target/zone-blitz-1.0-SNAPSHOT.jar

.PHONY: compile
compile: clean
	npm ci
	mvn dependency:resolve
	npm run build
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
