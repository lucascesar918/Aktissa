# Aktissa

[English](README.md) | [Português](README.pt-BR.md)

Aktissa é uma biblioteca Java leve para construir interfaces Swing com uma API fluente baseada em builders. Ela reduz a configuração repetitiva de componentes e layouts sem impedir o acesso aos componentes Swing subjacentes.

Aktissa é voltada a pessoas desenvolvedoras Java que já conhecem Swing e querem montar telas com menos código repetitivo. É uma biblioteca, não um framework de aplicação: sua aplicação continua controlando seu ponto de entrada e ciclo de vida.

## Recursos

- Builders fluentes para janelas, painéis, controles de entrada comuns, tabelas, diálogos e outros componentes.
- Estilos temáticos prontos, incluindo temas escuro, claro, de alto contraste e voltado a daltonismo.
- Callbacks simples para eventos e acesso aos componentes Swing subjacentes quando necessário.

## Requisitos

- Java 17 ou superior
- Maven

## Primeiros passos

Aktissa ainda não está publicada em um repositório Maven remoto. Instale-a no repositório Maven local a partir de um clone:

```sh
cd aktissa
mvn install
```

Depois, adicione a dependência ao `pom.xml` de outro projeto Maven:

```xml
<dependency>
    <groupId>io.github.aktissa</groupId>
    <artifactId>aktissa</artifactId>
    <version>1.2.1</version>
</dependency>
```

Continue pelo guia [Primeiros passos](docs/pt-BR/primeiros-passos.md) para ver uma tela interativa pequena e instruções para executar a demonstração do repositório.

## Demonstração

O repositório inclui uma demonstração interativa de gerenciamento de tarefas em [`Main.java`](aktissa/src/test/java/io/github/aktissa/demo/Main.java). Abra a classe em uma IDE e execute seu método `main`.

<img width="849" height="528" alt="Demonstração de gerenciamento de tarefas da Aktissa" src="https://github.com/user-attachments/assets/b04436d4-2f07-456b-ba22-7333fcd7b631" />

## Observação sobre temas

O tema padrão é escuro. Selecione outro tema antes de construir a interface. Alterar o tema atual não reaplica os estilos a componentes que já foram criados.

## Documentação

- [Getting Started (English)](docs/en/getting-started.md)
- [Components and Layouts (English)](docs/en/components-and-layouts.md)
- [Themes (English)](docs/en/themes.md)
- [Primeiros passos (Português)](docs/pt-BR/primeiros-passos.md)
- [Componentes e layouts (Português)](docs/pt-BR/componentes-e-layouts.md)
- [Temas (Português)](docs/pt-BR/temas.md)
- [README in English](README.md)