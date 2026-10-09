# Components and Layouts

<p align="center">
        <a href="components-and-layouts.md"><strong>🇬🇧 English</strong></a>
        &nbsp;·&nbsp;
        <a href="../pt-BR/componentes-e-layouts.md">🇧🇷 Português</a>
</p>

<p align="center"><a href="getting-started.md">Getting Started</a></p>

<hr>

Aktissa builders compose Swing components and provide common styling and event setup. Use a builder for the concise API, and use its `getRaw...()` method when you need a Swing operation that Aktissa does not wrap.

> **Design principle:** Aktissa removes repetitive Swing setup while leaving Swing components accessible.

## Windows and Panels

Create a window with `UI.window(title)`, set its content, and call `show()`:

```java
WindowBuilder window = UI.window("My Screen");
window.content(UI.panel().vertical()
        .add(UI.label("A clear heading").bold())
        .add(UI.textField("Project name", 24))
        .add(UI.button("Save").onClick(() -> UI.alert(window, "Saved", "Project settings were saved.")))
).show();
```

Without `size(width, height)`, the window packs to its content's preferred size. `show()` centers the window. The window closes the application by default when its close button is used.

`PanelBuilder` supports these layouts:

| Method | Layout | Behavior |
| --- | --- | --- |
| `vertical()` | Vertical `BoxLayout` | Adds 15-pixel spacing between children added without constraints. |
| `horizontal()` | Horizontal `BoxLayout` | Adds 15-pixel spacing between children added without constraints. |
| `flow()`, `flowLeft()`, `flowCenter()`, `flowRight()` | `FlowLayout` | Arranges children in a row and wraps them as needed; gaps are 15 pixels. `flow()` is left-aligned. |
| `grid(rows, cols)` | `GridLayout` | Equal-sized cells with 15-pixel gaps. |
| `border()` | `BorderLayout` | Uses 15-pixel gaps; add children with `BorderLayout` constraints. |

Use `title(text)` to add a themed titled border. `add(child, constraints)` accepts Swing `BorderLayout` constraints; unlike unconstrained `add(child)` in vertical and horizontal panels, it does not insert automatic spacing.

`add(...)` accepts either a Swing `Component` or an Aktissa `ComponentBuilder`. `build()` returns the underlying Swing component, so existing Swing components can be mixed into an Aktissa layout.

## Components

Factories are available on `UI`. The list below highlights the main construction and interaction methods; raw Swing accessors are available on many builders.

| Factory | Builder API |
| --- | --- |
| `UI.button(text)` | `onClick(Runnable)` |
| `UI.label(text)` | `text(...)`, `secondary()`, `muted()`, `accent()`, `bold()`, `fontScale(...)`, alignment methods |
| `UI.textField(label, columns)` | `text(...)`, `readOnly()`, `readWrite()`, `onEnter(Runnable)`, `horizontal()` |
| `UI.textFieldReadOnly(label, columns)` | Creates a read-only text field. |
| `UI.passwordField(label, columns)` | `horizontal()` |
| `UI.textArea(rows, columns)` | `readOnly()`, `readWrite()`; the area wraps lines and is placed in a scroll pane. |
| `UI.checkBox(text)` | `checked(boolean)`, `onToggle(Runnable)` |
| `UI.comboBox(label, items...)` | `onSelect(Consumer<String>)`, `horizontal()` |
| `UI.radioButton(text)` | `onSelect(Runnable)`, `accentText()`, `mutedText()` |
| `UI.slider(min, max, value)` | `onChange(Consumer<Integer>)`; callback runs when adjustment ends. |
| `UI.progressBar(min, max)` | `value(int)`, `indeterminate(boolean)` |
| `UI.table()` | `columns(String...)`, `row(Object...)`, `clear()` |
| `UI.scroll(component)` | `withBorder()` |

`UI.radioGroup()` creates a selection group, not a visible panel. Add the radio buttons returned by `group.add(text)` to a panel; use `group.getSelectedValue()` to read the selected text (or `null` if none is selected).

## State and Raw Swing Access

The convenience API is intentionally small, so state access varies by builder. For example, a text field's current value is read from the wrapped `JTextField`:

```java
TextFieldBuilder projectName = UI.textField("Project name", 24);
String value = projectName.getRawField().getText();
```

Other examples include `checkBox.getRawBox().isSelected()`, `comboBox.getRawComboBox().getSelectedItem()`, `slider.getRawSlider().getValue()`, and `table.getRawTable()`. Password values are exposed by Swing's `JPasswordField`; prefer its `getPassword()` method over converting the value to an immutable `String` when handling credentials.

For interactions, attach callbacks during construction:

```java
LabelBuilder estimate = UI.label("45 minutes").secondary();
SliderBuilder duration = UI.slider(15, 90, 45)
        .onChange(minutes -> estimate.text(minutes + " minutes"));
```

`onChange` on a slider invokes the callback after the user finishes adjusting it. Button, checkbox, combo box, radio button, and text-field callbacks use the corresponding Swing action/change events.

## Tables, Scrolling, and Dialogs

`TableBuilder` creates a `JTable`; it is not scroll-wrapped automatically. Wrap it explicitly when needed:

```java
TableBuilder tasks = UI.table()
        .columns("Task", "Status")
        .row("Prepare release", "In progress");

UI.panel().vertical()
        .add(UI.scroll(tasks).withBorder());
```

`UI.scroll(...)` accepts either a Swing `Component` or an Aktissa component builder. The scroll pane has no themed border unless `withBorder()` is used.

Use `UI.alert(parent, title, message)` for a simple modal alert and `UI.confirm(parent, title, message, onConfirm, cancelText, confirmText)` for a confirmation dialog. For custom content, use `UI.dialog(parent, title)` and add text or a component plus close/action buttons. Dialogs are modal; an action button runs its callback and then closes the dialog.

For theme presets and custom themes, see [Themes](themes.md).