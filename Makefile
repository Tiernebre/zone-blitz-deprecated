DBMATE=dbmate -e ZONE_BLITZ_POSTGRES_URL

.PHONY: run
run: install start

.PHONY: start
start:
	zone-blitz

.PHONY: debug
debug: install
	ZONE_BLITZ_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=7999 zone-blitz

.PHONY: development-environment
development-environment: install

.PHONY: install
install: migrate build
	cp -r build/install/zone-blitz/. /
	
.PHONY: build
build:
	npm ci
	npm run build
	gradle clean
	gradle installDist

.PHONY: migrate
migrate:
	$(DBMATE) --wait up
	gradle jooqCodegen

.PHONY: migration
migration:
	$(DBMATE) new $(NAME)

.PHONY: test
test:
	npm run lint
	gradle test

.PHONY: dev
dev:
	.devcontainer/dev.sh
