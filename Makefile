DBMATE=dbmate -e ZONE_BLITZ_POSTGRES_URL

.PHONY: run
run: install
	zone-blitz

.PHONY: debug
debug: install
	ZONE_BLITZ_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=7999 zone-blitz

.PHONY: install
install: build migrate
	cp -r build/install/zone-blitz/. /

.PHONY: development-environment
development-environment: build migrate
	
.PHONY: build
build:
	npm ci
	npm run build
	gradle installDist

.PHONY: test
test:
	npm run lint
	gradle test

.PHONY: migrate
migrate:
	$(DBMATE) --wait up
	gradle jooqCodegen

.PHONY: migration
migration:
	$(DBMATE) new $(NAME)

.PHONY: dev
dev:
	.devcontainer/dev.sh
