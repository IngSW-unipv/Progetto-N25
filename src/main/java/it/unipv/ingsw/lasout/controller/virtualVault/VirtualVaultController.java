package it.unipv.ingsw.lasout.controller.virtualVault;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.view.virtualVault.VirtualVaultItem;
import it.unipv.ingsw.lasout.view.virtualVault.VirtualVaultPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VirtualVaultController {

    private static VirtualVaultPanel view;
    private static VirtualVault virtualVault;

    public VirtualVaultController(VirtualVaultPanel view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Action listener per la JComboBox: al cambio selezione mostriamo un avviso
        view.addComboBoxListener(e-> {
            // Recupera l'oggetto selezionato
            VirtualVaultItem s = (VirtualVaultItem) view.getSelectedVault();

            if (s != null && s.getId() != -1) {
                virtualVault= LaVaultFacade.getInstance().getVirtualVaultFacade().getVirtualVault(new VirtualVault(s.getId()));
                System.out.println("Controllo id virtualVault selezionato: "+s.getId());
                setUpJInfoPanel();


                view.setNameLabelText(s.getName());
                view.getAddVirtualVaultButton().setEnabled(true);
                view.getDeleteVirtualVaultButton().setEnabled(true);
            }
        });

        // Action listener per il pulsante "Aggiungi VirtualVault"
        view.getAddVirtualVaultButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view, "Aggiungi VirtualVault eseguito!");
            }
        });

        // Action listener per il pulsante "Elimina VirtualVault"
        view.getDeleteVirtualVaultButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(view, "Elimina VirtualVault eseguito!");
            }
        });
    }


    /**
     * Caricamento
     */
    public static void load() {
        setUpComboBox();
    }
    /**
     * Riempimento e reset jcombobox
     */
    public static void setUpComboBox(){
        view.resetComboBox();
        view.addVirtualVaultItem(new VirtualVaultItem(-1, "Selezione VirtualVault"));
        List<VirtualVault> virtualVaultList = LaVaultFacade.getInstance().getVirtualVaultFacade().getAllVirtualVault(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
        for(VirtualVault v : virtualVaultList){
            System.out.println(v.getName()+v.getID());
            view.addVirtualVaultItem(new VirtualVaultItem(v.getID(), v.getName()));
        }
    }
    /**
     * Riempimento e reset panel al centro
     */
    private void setUpJInfoPanel() {
        view.resetInfoPanel();
        view.getInfoPanel().repaint();
        view.getInfoPanel().revalidate();

    }
}
