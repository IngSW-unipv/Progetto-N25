package it.unipv.ingsw.lasout.controller.group;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.view.group.GroupItem;
import it.unipv.ingsw.lasout.view.group.GroupPanel;
import it.unipv.ingsw.lasout.view.group.GroupSettingsDialog;

import javax.swing.JOptionPane;
import java.util.List;

public class GroupController {
    private GroupPanel groupPanel;
    private GroupSettingsDialog groupSettingsDialog;

    private Group activeGroup;

    public GroupController(GroupPanel groupPanel) {
        this.groupPanel = groupPanel;
        this.groupSettingsDialog=groupPanel.getSettingsDialog();
        initController();
    }

    private void initController() {

        groupPanel.addGroupItem(new GroupItem(-1, "Seleziona Gruppo..."));

        // Ascoltatore sulla comboBox:
        groupPanel.addComboBoxListener(e -> {
            // Recupera l'oggetto selezionato nel jcombobox
            GroupItem selected = (GroupItem) groupPanel.getSelectedGroup();

            if (selected != null && selected.getId() != -1) {
                activeGroup= LaVaultFacade.getInstance().getGroupFacade().getGroup(new Group(selected.getId()));
                System.out.println("Controllo id gruppo selezionato: "+selected.getId());

                groupPanel.setNameLabelText(activeGroup.getName());
                groupPanel.getImpostazioniBtn().setEnabled(true);

            }
        });

        // Quando clicco "Nuovo Gruppo"
        groupPanel.addNuovoGruppoListener(e ->
                JOptionPane.showMessageDialog(groupPanel, "Premuto: Nuovo Gruppo")
        );

        // Quando clicco "impostaz"
        groupPanel.addImpostazioniListener(e -> {
            groupSettingsDialog.setLocationRelativeTo(groupPanel);
            groupSettingsDialog.setVisible(true);
        });

        groupSettingsDialog.addOption1Listener(e -> JOptionPane.showMessageDialog(groupPanel, "Hai premuto Opzione 1"));
        groupSettingsDialog.addOption2Listener(e -> JOptionPane.showMessageDialog(groupPanel, "Hai premuto Opzione 2"));
        groupSettingsDialog.addOption3Listener(e -> JOptionPane.showMessageDialog(groupPanel, "Hai premuto Opzione 3"));

        // Quando clicco "Invita"
        groupPanel.addInvitaListener(e ->
                JOptionPane.showMessageDialog(groupPanel, "Premuto: Invita")
        );

        // Quando clicco "Aggiungi spesa"
        groupPanel.addAggiungiSpesaListener(e ->
                JOptionPane.showMessageDialog(groupPanel, "Premuto: Aggiungi spesa")
        );

        // Quando clicco "Finalizza"
        groupPanel.addFinalizzaListener(e ->
                JOptionPane.showMessageDialog(groupPanel, "Premuto: Finalizza")
        );
    }

    public void load(){
        setUpJComboBox();
    }

    public void setUpJComboBox(){
        List<Group> grouplist= LaVaultFacade.getInstance().getUserFacade().getGroupOfLoggedUser();
        for(Group group:grouplist){
            groupPanel.addGroupItem(new GroupItem((int) group.getId(), group.getName()));
        }
    }
}