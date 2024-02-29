.PHONY: run
run: install
	zone-blitz

.PHONY: install
install: build
	cp -r build/install/zone-blitz/. /

.PHONY: build
build:
	npm ci
	npm run build
	gradle installDist

.PHONY: test
test:
	npm run lint
	gradle test

.PHONY: format
format:
	npm run format
	
.PHONY: migrate
migrate:
	dbmate -e ZONE_BLITZ_POSTGRES_URL up
