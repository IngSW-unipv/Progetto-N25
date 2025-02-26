package it.unipv.ingsw.lasout.controller.virtualVault;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.virtualVault.ConcreteVirtualVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;
import it.unipv.ingsw.lasout.view.group.info.ExpenseRowPanel;
import it.unipv.ingsw.lasout.view.group.info.InfoRowPanel;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;
//import it.unipv.ingsw.lasout.view.virtualVault.AddNewVirtualVault;
import it.unipv.ingsw.lasout.view.vault.VaultPanel;
import it.unipv.ingsw.lasout.view.virtualVault.AddVirtualVaultDialog;
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
                System.out.println("SONO IL VV" + virtualVault);
                System.out.println("Controllo id virtualVault selezionato: " + s.getId());
                //Aggiorno il pannello centrale con i dettagli del virtualVault selezionato
                updateInfoPanel();

                view.setNameLabelText(s.getName());
                view.getAddVirtualVaultButton().setEnabled(true);
                view.getDeleteVirtualVaultButton().setEnabled(true);
            } else {
                virtualVault = null;
                updateInfoPanel();
                view.setNameLabelText("Seleziona VirtualVault");
                view.getAddVirtualVaultButton().setEnabled(false);
                view.getDeleteVirtualVaultButton().setEnabled(false);
            }

            view.getAddVirtualVaultButton().setEnabled(true);
            view.getDeleteVirtualVaultButton().setEnabled(true);
        });

        // ---------------Nuovo VirtualVault------------------
        // Listener per il pulsante "Aggiungi VirtualVault"
        view.getAddVirtualVaultButton().addActionListener(e -> {
            // Ottieni il frame principale per passarlo come parent al dialog
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
            AddVirtualVaultDialog dialog = new AddVirtualVaultDialog(parentFrame);

            // Aggiungi l'azione di salvataggio
            dialog.addSaveActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String nome = dialog.getVaultName();
                    double saldo = dialog.getVaultBalance();

                    if (nome.isEmpty() || saldo <= 0) {
                        JOptionPane.showMessageDialog(dialog, "Inserisci un nome valido e un saldo maggiore di zero.", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Recupera l'utente loggato
                    User loggedUser = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
                    //System.out.println(loggedUser.getId());
                    // Recupera il saldo disponibile dal vault principale
                    boolean available;
                    try {
                        available = LaVaultFacade.getInstance().getVaultFacade().ritiroVault(loggedUser, saldo);
                        //System.out.println("SONO LA VARIABILE D?APP"+available);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(dialog, "Errore nel recuperare il saldo del Vault principale.", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Controllo: se l'importo inserito è maggiore di quello disponibile, mostra errore e consenti di reinserire
                    if (!available) {
                        JOptionPane.showMessageDialog(dialog, "Importo troppo alto!\nHai a disposizione: " + available + "\nReinserisci un importo valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Crea un nuovo VirtualVault e imposta i dati
                    VirtualVault nuovoVault = new VirtualVault();
                    nuovoVault.setName(nome);
                    nuovoVault.setBalance(saldo);
                    System.out.println("SONO IL BALANCE DEL NUOVO VAULT"+ nuovoVault.getBalance());
                    // Imposta l'owner dall'utente loggato
                    User loggedUser1 = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
                    nuovoVault.setOwner(loggedUser1);

                    // Salva il nuovo vault tramite il facade
                    boolean success = LaVaultFacade.getInstance().getVirtualVaultFacade().newVirtualVault(nuovoVault, loggedUser1);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "VirtualVault creato con successo!");
                        dialog.dispose();
                        /*Righe per aggiornare il saldo VAULT madre
                        VaultPanel vaultPanel = new VaultPanel();
                        vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getBalanceByUserId(loggedUser1));*/
                        // Aggiorna la combo box per includere il nuovo vault
                        setUpComboBox();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Errore nella creazione del VirtualVault.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            dialog.setVisible(true);
        });
    }


        /**
         * Caricamento
         */
        public static void load () {
            setUpComboBox();
    		
            //vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getBalanceByUserId(currentUser));

        }

        /**
         * Riempimento e reset jcombobox
         */
        public static void setUpComboBox () {
            view.resetComboBox();
            //Aggiunge l'elemento di default
            view.addVirtualVaultItem(new VirtualVaultItem(-1, "Selezione VirtualVault"));
            List<VirtualVault> virtualVaultList = LaVaultFacade.getInstance().getVirtualVaultFacade().getAllVirtualVault(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
            System.out.println("VirtualVault trovati" + virtualVaultList.size());
            for (VirtualVault v : virtualVaultList) {
                //System.out.println(v.getName()+v.getID());
                view.addVirtualVaultItem(new VirtualVaultItem(v.getID(), v.getName()));
            }
        }

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
                JLabel balanceLabel = new JLabel("Saldo: " + virtualVault.getBalance() + "€");
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

