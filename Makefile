.PHONY: serve
serve: compile run

.PHONY: run
run:
	gradle run

.PHONY: compile
compile: install build

.PHONY: build
build:
	npm run build
	gradle build

.PHONY: test
test:
	npm run lint
	gradle test

.PHONY: install
install:
	npm ci
	
.PHONY: format
format:
	npm run format
