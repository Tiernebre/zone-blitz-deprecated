.PHONY: run
run: build-client
	gradle run --no-daemon
	
.PHONY: build
build: build-client
	gradle build

.PHONY: build-client
build-client:
	npm run build

.PHONY: install
install:
	npm ci

.PHONY: test
test:
	npm run lint
	gradle test

.PHONY: format
format:
	npm run format
	
.PHONY: dev
dev:
	./.devcontainer/dev.sh
