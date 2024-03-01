DBMATE=dbmate -e ZONE_BLITZ_POSTGRES_URL

.PHONY: run
run: install
	zone-blitz

.PHONY: install
install: build migrate
	cp -r build/install/zone-blitz/. /

.PHONY: development-environment
development-environment: migrate build
	
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
	make format

.PHONY: migration
migration:
	$(DBMATE) new $(NAME)

.PHONY: format
format:
	npm run format
