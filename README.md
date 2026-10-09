# Aktissa

<p align="center">
    <a href="README.md"><strong>🇬🇧 English</strong></a>
    &nbsp;·&nbsp;
    <a href="README.pt-BR.md">🇧🇷 Português</a>
</p>

<p align="center">Fluent builders for faster Java Swing interfaces</p>

<hr>

Aktissa is a lightweight Java library for building Swing interfaces with a fluent builder API. It reduces repetitive component and layout setup while keeping the underlying Swing components accessible.

Aktissa is intended for Java developers who already know Swing and want to build screens with less boilerplate. It is a library, not an application framework: your application keeps control of its entry point and lifecycle.

## Features

- Fluent builders for windows, panels, common input controls, tables, dialogs, and more.
- Sensible themed defaults, including dark, light, high-contrast, and colorblind-oriented themes.
- Simple event callbacks and access to the underlying Swing components when needed.

## Requirements

- Java 17 or later
- Maven
- Git

## Get Started

> **Not published yet:** Aktissa is not available from a remote Maven repository. Clone the GitHub repository and install the Maven module into your local repository:

```sh
git clone https://github.com/lucascesar918/aktissa.git
cd aktissa/aktissa
mvn install
```

The first `aktissa` directory is the cloned repository; the second is the Maven module containing `pom.xml`.

Then add it to another Maven project's `pom.xml`:

```xml
<dependency>
    <groupId>io.github.aktissa</groupId>
    <artifactId>aktissa</artifactId>
    <version>1.2.1</version>
</dependency>
```

Continue with the [Getting Started guide](docs/en/getting-started.md) for a small interactive screen and notes on running the repository demo.

## Demo

The repository includes an interactive task-management demo in [`TaskManagerDemo.java`](aktissa/src/test/java/io/github/aktissa/demo/TaskManagerDemo.java). Open the class in an IDE and run its `main` method.

<p align="center">
    <img width="849" height="528" alt="Aktissa task management demo" src="https://github.com/user-attachments/assets/b04436d4-2f07-456b-ba22-7333fcd7b631" />
</p>

## Theme Note

The default theme is dark. Choose a different theme before constructing the interface. Changing the current theme does not restyle components that have already been created.

## Documentation

- [Getting Started (English)](docs/en/getting-started.md)
- [Components and Layouts (English)](docs/en/components-and-layouts.md)
- [Themes (English)](docs/en/themes.md)
- [Screenshot Gallery (English)](docs/en/screenshots.md)
- [Primeiros passos (Português)](docs/pt-BR/primeiros-passos.md)
- [Componentes e layouts (Português)](docs/pt-BR/componentes-e-layouts.md)
- [Temas (Português)](docs/pt-BR/temas.md)
- [Galeria de capturas (Português)](docs/pt-BR/capturas-de-tela.md)
- [README em português](README.pt-BR.md)
