DBMATE=dbmate -e ZONE_BLITZ_POSTGRES_URL
FILES_TO_FORMAT=**/*.{java,xml,md,yml,mustache,sh,ts}

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

.PHONY: install
install: clean migrate build
	cp -r build/install/zone-blitz/. /
	
build: node_modules
	cp node_modules/htmx.org/dist/htmx.min.js src/main/resources/assets/htmx.js 
	cp node_modules/@unocss/reset/tailwind.css src/main/resources/assets/reset.css
	npx unocss \"src/main/resources/templates/**/*.mustache\" -o src/main/resources/assets/styles.css
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
	npx prettier --check $(FILES_TO_FORMAT)
	gradle test

.PHONY: e2e
e2e: node_modules
	npx playwright test $(TEST)

.PHONY: dev
dev:
	.devcontainer/dev.sh

.PHONY: clean
clean:
	gradle clean
	rm -fr build
	
.PHONY: fmt
fmt: format

.PHONY: format
format: node_modules
	npx prettier --write $(FILES_TO_FORMAT)
	
node_modules:
	npm ci