package it.unipv.ingsw.lasout.view.cashbook;

import javax.swing.*;
import it.unipv.ingsw.lasout.model.cashbook.Cashbook;
import it.unipv.ingsw.lasout.model.user.User;

public class CashbookPanel extends JPanel {
    private JComboBox<Cashbook> cashbookComboBox;

    public CashbookPanel(User user) {
    }

    // Getter per il JComboBox
    public JComboBox<Cashbook> getCashbookComboBox() {
        return cashbookComboBox;
    }
}

