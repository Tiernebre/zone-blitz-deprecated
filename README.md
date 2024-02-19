# [zone-blitz](https://zoneblitz.app/)

A free (as in freedom AND beer!) and open source american football general manager simulator, built entirely with and served on web technologies!

## Description

You can think of this simulator as being the american football version of popular games like
[Out of the Park Baseball](https://www.ootpdevelopments.com/out-of-the-park-baseball-home/) or
[Football Manager](https://www.footballmanager.com/).

### Status

Zone Blitz is in a pre-alpha stage of development, and still needs to implement even basic
business logic. See [roadmap](#roadmap) for more details!

### Roadmap

Zone Blitz still has a _ways_ to go, and is **currently not a working proof-of-concept product**. Progress
is being made steadily though! Below are the high level goals I wish to accomplish before calling
Zone Blitz a working minimum viable alpha proof-of-concept:

- Game logic and rules engine with accurate and realistic statistics.
- Management logic to set depth charts, rosters, coaching staffs, free agency, and drafting.
- Player progression and regression based on performance, age, and other factors.
- Ability to simulate full seasons with AI / CPU managed teams with working year-to-year progression and schedule management.
- Web interface for users to login and manage their leagues and teams.

For an alpha launch, the above goals are going to be purposefully _just good enough_ in exchange
for getting a working proof-of-concept out soon.

Beta launch will start to really hone in and refine the quality of the goals above, along with
brainstorming some brand new, innovative goals.

### Technology stack

- [Java via OpenJDK](https://openjdk.org/)
- [HTMX](https://htmx.org/)
- [UnoCSS](https://unocss.dev/)

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

## Contributing

Contributing is currently closed off _until_ an alpha launch has been fully published. Please see the above [roadmap](#roadmap) for more details.

## Authors

- [Brendan Tierney](https://tiernebre.com)

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE Version 3. See [LICENSE](LICENSE) for details.
