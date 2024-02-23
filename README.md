# [zone-blitz](https://zoneblitz.app/)

A free (as in freedom _and_ beer) and open source american football general manager simulator, built entirely with and served on web technologies!

## Description

You can think of this simulator as being the american football version of popular games like
[Out of the Park Baseball](https://www.ootpdevelopments.com/out-of-the-park-baseball-home/) or
[Football Manager](https://www.footballmanager.com/).

### Status

Zone Blitz is in a pre-alpha stage of development, and still needs to implement even basic
business logic. See [roadmap](./docs/roadmap.md) for more details!

### [Documentation](./docs/README.md)

If you wish to learn in great (and sometimes verbose) detail more about the technical direction, product roadmap, explanations of decisions made, or
want to learn about my mindset with developing software products do check out the [documentation](./docs/README.md)!

## Getting started

### Developing

Follow the instructions below if you wish to develop and contribute to zone blitz.

#### Dependencies

- [docker](https://www.docker.com/products/docker-desktop/)
- [vscode](https://code.visualstudio.com/)
  - Dev containers is the supported containerized tooling for zone blitz. Using a different IDE could work, but is not and will not be supported.

#### Environment

1. Clone the repository.
   - `git clone https://github.com/Tiernebre/zone-blitz`
2. Open the cloned folder within VSCode.
3. VSCode will prompt you about the dev container support. Run the project within the dev container.
   - if not prompted, execute the shortcut `Cmd + Shift + P` then type `Dev Containers: Rebuild and Reopen`.
4. Wait for the container window to load and the post create compilation command to finish.
5. Run `make` to serve zone blitz. You should start seeing logs and eventually see a prompt for the Java server running.
6. Navigate to `http://localhost:8000` in a browser of your choice. You should now see zone blitz running in development mode.

#### Hot Reload Support

If you don't want to run `make` everytime you make changes, you can run this script which will watch for file changes
and restart the server to reflect your new changes:

```sh
./bin/dev.sh
```

#### Testing

You can run the unit testing suite by just running the following command in your [development environment](#environment).

```sh
make test
```

## Contributing

Contributing is currently closed off _until_ an alpha launch has been fully published. Please see the above [roadmap](./docs/roadmap.md) for more details.

## Authors

- [Brendan Tierney](https://tiernebre.com)

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE Version 3. See [LICENSE](LICENSE) for details.
