.PHONY: run
run: build install
	zone-blitz

.PHONY: build
build:
	npm ci
	npm run build
	gradle installDist

.PHONY: install
install:
	cp -r build/install/zone-blitz/. /

.PHONY: test
test:
	npm run lint
	gradle test

.PHONY: format
format:
	npm run format
	