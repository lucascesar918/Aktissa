package io.github.aktissa.builders;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import io.github.aktissa.theme.Theme;

public class TableBuilder {
    private final JTable table;
    private final DefaultTableModel model;

    public TableBuilder() {
        this.model = new DefaultTableModel();
        this.table = new JTable(this.model);

        this.table.setBackground(Theme.BACKGROUND_COMPONENT);
        this.table.setForeground(Theme.TEXT_PRIMARY);
        this.table.setGridColor(Theme.BORDER_COLOR);
        this.table.setSelectionBackground(Theme.ACCENT_CYAN);
        this.table.setSelectionForeground(Color.BLACK);
        
        this.table.setFillsViewportHeight(true);
        this.table.setRowHeight(25);
        this.table.setShowVerticalLines(false);

        JTableHeader header = this.table.getTableHeader();
        header.setBackground(Theme.BACKGROUND_BASE);
        header.setForeground(Theme.TEXT_SECONDARY);
        header.setBorder(Theme.defaultLineBorder());
    }

    public TableBuilder columns(String... columnNames) {
        for (String name : columnNames) {
            this.model.addColumn(name);
        }
        return this;
    }

    public TableBuilder row(Object... rowData) {
        this.model.addRow(rowData);
        return this;
    }

    public JTable getRawTable() {
        return this.table;
    }

    public JComponent build() {
        return this.table;
    }
}