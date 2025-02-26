package it.unipv.ingsw.lasout.controller.virtualVault;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.view.group.info.ExpenseRowPanel;
import it.unipv.ingsw.lasout.view.group.info.InfoRowPanel;
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
        //Popolo la comboBox al caricamento
        //setUpComboBox();
        // Action listener per la JComboBox: al cambio selezione mostriamo un avviso
        view.addComboBoxListener(e -> {
            // Recupera l'oggetto selezionato
            VirtualVaultItem s = (VirtualVaultItem) view.getSelectedVault();

            if (s != null && s.getId() != -1) {
                //Crea un oggetto VirtualVault temporaneo e imposta l'ID
                VirtualVault tempVault = new VirtualVault();
                tempVault.setID(s.getId());
                tempVault.setOwner(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
                //System.out.println(s.getId());
                //Recupero il VirtualVault completo tramite la Facade utilizzando il tempValue
                virtualVault = LaVaultFacade.getInstance().getVirtualVaultFacade().getVirtualVault(tempVault);
                System.out.println("SONO IL VV"+ virtualVault);
                System.out.println("Controllo id virtualVault selezionato: " + s.getId());
                //Aggiorno il pannello centrale con i dettagli del virtualVault selezionato
                updateInfoPanel();

                view.setNameLabelText(s.getName());
                view.getAddVirtualVaultButton().setEnabled(true);
                view.getDeleteVirtualVaultButton().setEnabled(true);
            }else{
                virtualVault = null;
                updateInfoPanel();
                view.setNameLabelText("Seleziona VirtualVault");
                view.getAddVirtualVaultButton().setEnabled(false);
                view.getDeleteVirtualVaultButton().setEnabled(false);
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
    public static void setUpComboBox() {
        view.resetComboBox();
        //Aggiunge l'elemento di default
        view.addVirtualVaultItem(new VirtualVaultItem(-1, "Selezione VirtualVault"));
        List<VirtualVault> virtualVaultList = LaVaultFacade.getInstance().getVirtualVaultFacade().getAllVirtualVault(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
        System.out.println("VirtualVault trovati"+virtualVaultList.size());
        for (VirtualVault v : virtualVaultList) {
            //System.out.println(v.getName()+v.getID());
            view.addVirtualVaultItem(new VirtualVaultItem(v.getID(), v.getName()));
        }
    }

    /**
     * Riempimento e reset panel al centro
     */
    /*private void setUpJInfoPanel() {
        view.resetInfoPanel();
        view.getInfoPanel().repaint();
        view.getInfoPanel().revalidate();
        List<VirtualVault> virtualVaultList = LaVaultFacade.getInstance().getVirtualVaultFacade().getAllVirtualVault(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
        for (VirtualVault v : virtualVaultList) {

        }
    }*/
    /**
     * Aggiorna il pannello centrale (infoPanel) con le informazioni del VirtualVault selezionato.
     */
    private void updateInfoPanel() {
        // Pulisce il pannello informativo
        view.resetInfoPanel();
        JPanel infoPanel = view.getInfoPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        if (virtualVault != null) {
            // Crea ed aggiunge le etichette con le informazioni del VirtualVault
            //JLabel idLabel = new JLabel("ID: " + virtualVault.getID());
            JLabel nameLabel = new JLabel("Nome: " + virtualVault.getName());
            JLabel balanceLabel = new JLabel("Saldo: " + virtualVault.getBalance()+"€");
            //infoPanel.add(idLabel);
            infoPanel.add(nameLabel);
            infoPanel.add(balanceLabel);
        } else {
            // Visualizza un messaggio di default se nessun VirtualVault è selezionato
            infoPanel.add(new JLabel("Nessun Virtual Vault selezionato"));
        }
        infoPanel.revalidate();
        infoPanel.repaint();
    }
}

