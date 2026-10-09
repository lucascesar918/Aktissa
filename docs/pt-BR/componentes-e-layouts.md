# Componentes e layouts

<p align="center">
        <a href="../en/components-and-layouts.md">🇬🇧 English</a>
        &nbsp;·&nbsp;
        <a href="componentes-e-layouts.md"><strong>🇧🇷 Português</strong></a>
</p>

<p align="center"><a href="primeiros-passos.md">Primeiros passos</a></p>

<hr>

Os builders da Aktissa compõem componentes Swing e oferecem estilos e configuração de eventos comuns. Use um builder para a API concisa e acesse `getRaw...()` quando precisar de uma operação Swing que a Aktissa não encapsula.

> **Princípio de projeto:** a Aktissa remove a configuração repetitiva do Swing sem esconder seus componentes.

## Janelas e painéis

Crie uma janela com `UI.window(titulo)`, defina seu conteúdo e chame `show()`:

```java
WindowBuilder window = UI.window("Minha tela");
window.content(UI.panel().vertical()
        .add(UI.label("Um título claro").bold())
        .add(UI.textField("Nome do projeto", 24))
        .add(UI.button("Salvar").onClick(() -> UI.alert(window, "Salvo", "As configurações do projeto foram salvas.")))
).show();
```

Sem `size(largura, altura)`, a janela se ajusta ao tamanho preferencial do conteúdo. `show()` centraliza a janela. Por padrão, usar o botão de fechar encerra a aplicação.

`PanelBuilder` oferece estes layouts:

| Método | Layout | Comportamento |
| --- | --- | --- |
| `vertical()` | `BoxLayout` vertical | Adiciona espaçamento de 15 pixels entre filhos adicionados sem restrições. |
| `horizontal()` | `BoxLayout` horizontal | Adiciona espaçamento de 15 pixels entre filhos adicionados sem restrições. |
| `flow()`, `flowLeft()`, `flowCenter()`, `flowRight()` | `FlowLayout` | Organiza os filhos em uma linha e quebra quando necessário; os intervalos são de 15 pixels. `flow()` alinha à esquerda. |
| `grid(linhas, colunas)` | `GridLayout` | Células de mesmo tamanho com intervalos de 15 pixels. |
| `border()` | `BorderLayout` | Usa intervalos de 15 pixels; adicione filhos com restrições do `BorderLayout`. |

Use `title(texto)` para adicionar uma borda temática com título. `add(filho, restricoes)` aceita restrições do `BorderLayout` do Swing; diferente de `add(filho)` em painéis verticais e horizontais, essa sobrecarga não insere espaçamento automático.

`add(...)` aceita tanto um `Component` do Swing quanto um `ComponentBuilder` da Aktissa. `build()` retorna o componente Swing subjacente, permitindo misturar componentes Swing existentes em um layout Aktissa.

## Componentes

As fábricas estão disponíveis em `UI`. A lista destaca os principais métodos de construção e interação; muitos builders também expõem acesso ao Swing subjacente.

| Fábrica | API do builder |
| --- | --- |
| `UI.button(texto)` | `onClick(Runnable)` |
| `UI.label(texto)` | `text(...)`, `secondary()`, `muted()`, `accent()`, `bold()`, `fontScale(...)` e métodos de alinhamento |
| `UI.textField(rotulo, colunas)` | `text(...)`, `readOnly()`, `readWrite()`, `onEnter(Runnable)`, `horizontal()` |
| `UI.textFieldReadOnly(rotulo, colunas)` | Cria um campo de texto somente leitura. |
| `UI.passwordField(rotulo, colunas)` | `horizontal()` |
| `UI.textArea(linhas, colunas)` | `readOnly()`, `readWrite()`; a área quebra linhas e fica dentro de um painel de rolagem. |
| `UI.checkBox(texto)` | `checked(boolean)`, `onToggle(Runnable)` |
| `UI.comboBox(rotulo, itens...)` | `onSelect(Consumer<String>)`, `horizontal()` |
| `UI.radioButton(texto)` | `onSelect(Runnable)`, `accentText()`, `mutedText()` |
| `UI.slider(min, max, valor)` | `onChange(Consumer<Integer>)`; o callback é chamado quando o ajuste termina. |
| `UI.progressBar(min, max)` | `value(int)`, `indeterminate(boolean)` |
| `UI.table()` | `columns(String...)`, `row(Object...)`, `clear()` |
| `UI.scroll(componente)` | `withBorder()` |

`UI.radioGroup()` cria um grupo de seleção, não um painel visível. Adicione ao painel os botões retornados por `grupo.add(texto)`; use `grupo.getSelectedValue()` para ler o texto selecionado (ou `null` se nenhum estiver selecionado).

## Estado e acesso ao Swing

A API de conveniência é intencionalmente pequena, então o acesso ao estado varia conforme o builder. Por exemplo, o valor atual de um campo é lido pelo `JTextField` encapsulado:

```java
TextFieldBuilder nomeProjeto = UI.textField("Nome do projeto", 24);
String valor = nomeProjeto.getRawField().getText();
```

Outros exemplos são `checkBox.getRawBox().isSelected()`, `comboBox.getRawComboBox().getSelectedItem()`, `slider.getRawSlider().getValue()` e `table.getRawTable()`. Valores de senha são expostos pelo `JPasswordField` do Swing; ao lidar com credenciais, prefira seu método `getPassword()` em vez de converter o valor para uma `String` imutável.

Para interações, registre callbacks durante a construção:

```java
LabelBuilder estimate = UI.label("45 minutos").secondary();
SliderBuilder duration = UI.slider(15, 90, 45)
        .onChange(minutes -> estimate.text(minutes + " minutos"));
```

O `onChange` do slider chama o callback quando a pessoa termina o ajuste. Callbacks de botão, checkbox, combo box, botão de opção e campo de texto usam os eventos correspondentes do Swing.

## Tabelas, rolagem e diálogos

`TableBuilder` cria um `JTable`; ele não é colocado automaticamente em um painel de rolagem. Envolva-o explicitamente quando necessário:

```java
TableBuilder tasks = UI.table()
        .columns("Tarefa", "Status")
        .row("Preparar versão", "Em andamento");

UI.panel().vertical()
        .add(UI.scroll(tasks).withBorder());
```

`UI.scroll(...)` aceita um `Component` Swing ou um builder de componente Aktissa. O painel de rolagem não tem borda temática, a menos que `withBorder()` seja usado.

Use `UI.alert(pai, titulo, mensagem)` para um alerta modal simples e `UI.confirm(pai, titulo, mensagem, onConfirm, textoCancelar, textoConfirmar)` para uma confirmação. Para conteúdo personalizado, use `UI.dialog(pai, titulo)` e adicione texto ou um componente, além de botões de fechar/ação. Os diálogos são modais; o botão de ação executa seu callback e depois fecha o diálogo.

Para temas prontos e temas personalizados, consulte [Temas](temas.md).