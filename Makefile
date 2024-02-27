.PHONY: run
run: build-client serve
	gradle run
	
.PHONY: build
compile: build-client
	gradle build

.PHONY: build-client
build-client:
	npm run build

.PHONY: install-client
install-client:
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
