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
	.devcontainer/addProxyHost.sh
	.devcontainer/installBrowserCertifications.sh

.PHONY: install
install: clean migrate build
	cp -r build/install/zone-blitz/. /
	
build: node_modules
	npm run build
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

.PHONY: e2e
e2e: node_modules
	npm run e2e

.PHONY: dev
dev:
	.devcontainer/dev.sh
	
.PHONY: clean
clean:
	gradle clean
	rm -fr build
	
node_modules:
	npm ci
