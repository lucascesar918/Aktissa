package io.github.aktissa.builders;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import io.github.aktissa.theme.Theme;
import io.github.aktissa.theme.ThemeManager;

public class TableBuilder {
    private final JTable table;
    private final DefaultTableModel model;

    public TableBuilder() {
        this.model = new DefaultTableModel();
        this.table = new JTable(this.model);

        this.table.setBackground(ThemeManager.current().backgroundComponent());
        this.table.setForeground(ThemeManager.current().textPrimary());
        this.table.setGridColor(ThemeManager.current().border());
        this.table.setSelectionBackground(ThemeManager.current().accent());
        this.table.setSelectionForeground(ThemeManager.current().textSecondary());
        
        this.table.setFillsViewportHeight(true);
        this.table.setRowHeight(25);
        this.table.setShowVerticalLines(false);

        JTableHeader header = this.table.getTableHeader();
        header.setBackground(ThemeManager.current().backgroundBase());
        header.setForeground(ThemeManager.current().textSecondary());
        header.setBorder(ThemeManager.current().defaultLineBorder());
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