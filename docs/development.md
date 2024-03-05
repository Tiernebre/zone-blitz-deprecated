# Developing on Zone Blitz

Follow the instructions below if you wish to develop and contribute to zone
blitz.

## Dependencies

- [docker](https://www.docker.com/products/docker-desktop/)
- [vscode](https://code.visualstudio.com/)
  - [Dev containers](https://code.visualstudio.com/docs/devcontainers/containers)
    is the supported containerized tooling for zone blitz. Using a different IDE
    could work, but is not and will not be supported.
- [mkcert](https://github.com/FiloSottile/mkcert)
  - `mkcert` is used to establish a localhost HTTPS certificate that is used
    within the proxy. This helps ensure the dev environment is consistent with
    production.

## Development Environment

1. Install `mkcert`. This tool is used to help get a working local HTTPS
   certification.
   - [mkcert installation documentation](https://github.com/FiloSottile/mkcert?tab=readme-ov-file#installation)
2. Clone the repository.
   - `git clone https://github.com/Tiernebre/zone-blitz`
3. Open the cloned folder within VSCode.
4. VSCode will prompt you about the dev container support. Run the project
   within the dev container.
   - if not prompted, execute the shortcut `Cmd + Shift + P` then type
     `Dev Containers: Rebuild and Reopen`.
5. You'll notice in the terminal prompt that the SSL certificates and DNS host
   for local development is automated within the dev container setup, and **does
   require sudo access**. The scripts provide guidance if you do not trust and
   want to manually perform the steps rather than it being automated. These
   steps are needed to ensure a working, consistent, and accurate development
   experience.
   - This is a first-time step, any proceeding dev container runs and builds
     will not need the sudo request unless you re-clone or are on a new machine.
6. Wait for the container window to finish loading. **This can take a few
   minutes on your first run as it needs to download any Docker image
   dependencies.**
7. Run `make` to serve zone blitz. You should start seeing logs and eventually
   see a prompt for the Java server running.
8. **Navigate to https://dev.zoneblitz.app a browser of your choice**. You
   should now see your locally running copy of the zone blitz website.

## Development Philosophy and Direction

If you're curious about _why_ I chose a specific technology or approach to solving a problem, I have documented
my approaches in the [architecture documentation](./architecture/README.md). These articles can help
provide guidance if you are developing a new feature or fixing a bug on Zone Blitz.

### Debug Support

For debugging you will want to run `make debug` instead of `make`, then you'll
want to open the "Run and Debug" menu in your VSCode window and run the "Debug (attach)" command.

The application will not start until your debugger is attached. From there, you can use VSCode's
debug tooling to debug the app.

### Traefik Proxy Development

You can access the Traefik web dashboard in http://0.0.0.0:8080 for debugging
and visually observing your local Traefik instance that runs within the dev
container environment.

### Database Development

The PostgreSQL database instance is available at `postgres://dev:password@database:5432/zone_blitz`.

You can also use an already spun up instance of [pgweb](https://github.com/sosedoff/pgweb) at http://0.0.0.0:3000
that is already connected to the database instance in the development container.

#### Migrations

Database migrations are managed through [dbmate](https://github.com/amacneil/dbmate). It is already installed
within the development container environment for you.

To create a new migration, simply run:

```sh
make migration NAME=<name of the new migration>
```

To run migrations, simply run:

```sh
make migrate
```

#### jOOQ Code Generation

We're using [jOOQ](https://www.jooq.org/) as our main database query driver tool. jOOQ generates code based
upon a database schema.

When you run the development environment, everytime you [make a database migration change](#migrations) the
jOOQ code will auto generate for you.

At this point, we are **specifically tracking code generated from jOOQ in version control**. [This has some benefits
and drawbacks](https://www.jooq.org/doc/latest/manual/code-generation/codegen-version-control/), and for the state
that Zone Blitz is in I'm OK with it but could change direction as the project becomes more complex.

You can find generated jOOQ code in the package `com.tiernebre.database.jooq`.

## Testing

You can run the unit testing suite by just running the following command in your
[development environment](#environment).

```sh
make test
```
