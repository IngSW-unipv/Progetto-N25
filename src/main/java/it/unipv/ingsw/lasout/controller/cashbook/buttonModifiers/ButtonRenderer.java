package it.unipv.ingsw.lasout.controller.cashbook.buttonModifiers;

import it.unipv.ingsw.lasout.view.LaColor;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

// Classe apposita per visualizzare il bottone all'interno di una cella
public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "Edit" : value.toString());
        setBackground(LaColor.BTN_SFONDO);
        setForeground(LaColor.FONT);
        return this;
    }
}
