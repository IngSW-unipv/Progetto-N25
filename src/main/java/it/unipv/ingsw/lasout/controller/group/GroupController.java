package it.unipv.ingsw.lasout.controller.group;

import it.unipv.ingsw.lasout.view.group.GroupPanel;
import javax.swing.JOptionPane;

public class GroupController {
    private GroupPanel groupPanel;

    public GroupController(GroupPanel groupPanel) {
        this.groupPanel = groupPanel;
        initController();
    }

    private void initController() {
        // Aggiungi un listener in linea al bottone
        groupPanel.addActionButtonListener(e ->
                JOptionPane.showMessageDialog(groupPanel, "Azione eseguita dal GroupPanel!")

        );
    }
}
