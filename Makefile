.PHONY: run
run: build-client 
	gradle run

.PHONY: build
build: build-client
	gradle build

.PHONY: build-client
build-client:
	npm ci
	npm run build

.PHONY: test
test:
	npm run lint
	gradle test

.PHONY: format
format:
	npm run format
