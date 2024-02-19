# zone-blitz

An american football general manager simulator, built entirely with and served on web technologies!

## Description

You can think of this simulator as being the american football version of popular games like
[Out of the Park Baseball](https://www.ootpdevelopments.com/out-of-the-park-baseball-home/) or
[Football Manager](https://www.footballmanager.com/).

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
