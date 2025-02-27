package it.unipv.ingsw.lasout.controller.virtualVault;

import it.unipv.ingsw.lasout.controller.vault.VaultController;
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
import it.unipv.ingsw.lasout.view.virtualVault.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
                //System.out.println("SONO IL VV" + virtualVault);
                //System.out.println("Controllo id virtualVault selezionato: " + s.getId());
                //Aggiorno il pannello centrale con i dettagli del virtualVault selezionato
                //updateInfoPanel();
                view.updateCentralInfo(virtualVault.getName(), "Saldo: " + virtualVault.getBalance() + "€");

                view.setNameLabelText(s.getName());
                view.getAddVirtualVaultButton().setEnabled(true);
                view.getDeleteVirtualVaultButton().setEnabled(true);
            } else {
                virtualVault = new VirtualVault();
                //updateInfoPanel();
                view.updateCentralInfo(virtualVault.getName(), "Saldo: " + virtualVault.getBalance() + "€");
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
                    //System.out.println("SONO IL VV"+nuovoVault.getBalance() + nuovoVault);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "VirtualVault creato con successo!");
                        dialog.dispose();
                        //Righe per aggiornare il saldo VAULT madre
                        VaultController.load();
                        // Aggiorna la combo box per includere il nuovo vault
                        setUpComboBox();
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Errore nella creazione del VirtualVault.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            dialog.setVisible(true);
        });



        //------------Eliminare VirtualVault-------------

        view.getDeleteVirtualVaultButton().addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
            // Prepara la lista degli elementi da mostrare (filtrando l'elemento di default)
            List<VirtualVaultItem> vaultItems = new ArrayList<>();
            for (int i = 0; i < view.getVaultComboBox().getItemCount(); i++) {
                VirtualVaultItem item = view.getVaultComboBox().getItemAt(i);
                System.out.println("io so di "+item.getId());
                if (item.getId() > 0) {
                    vaultItems.add(item);
                }
                for(VirtualVaultItem v : vaultItems) {
                    System.out.println(v.getId()+""+v+"");
                }

            }


            DeleteVirtualVaultDialog dialog = new DeleteVirtualVaultDialog(parentFrame, vaultItems);
            dialog.addDeleteActionListener(f-> {

                    VirtualVaultItem selectedItem = dialog.getSelectedVaultItem();
                    System.out.println("SONO IL VV" + selectedItem.getId());
                    if (selectedItem != null && selectedItem.getId() > 0) {
                        // Recupera il VirtualVault completo da eliminare
                        VirtualVault tempVault = new VirtualVault();
                        tempVault=LaVaultFacade.getInstance().getVirtualVaultFacade().getVirtualVault(new VirtualVault(LaVaultFacade.getInstance().getUserFacade().getUser(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser()), selectedItem.getId()));
                        //System.out.println("SONO IL VV" + tempVault);

                        if (tempVault == null) {
                            JOptionPane.showMessageDialog(dialog, "Non è stato possibile recuperare il VirtualVault selezionato.", "Errore", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        // Recupera il saldo del VirtualVault da eliminare
                        double balanceToDeposit = tempVault.getBalance();
                        User loggedUser = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
                        boolean depositSuccess;
                        try {
                            // Effettua il deposito del balance del vault da eliminare nel vault principale
                            depositSuccess = LaVaultFacade.getInstance().getVaultFacade().depositoVault(loggedUser, balanceToDeposit);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(dialog,
                                    "Errore nel reintegrare il saldo nel Vault principale.",
                                    "Errore", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (!depositSuccess) {
                            JOptionPane.showMessageDialog(dialog,
                                    "Impossibile reintegrare il saldo del VirtualVault da eliminare.",
                                    "Errore", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        // Elimina il VirtualVault tramite il facade
                        boolean success = LaVaultFacade.getInstance().getVirtualVaultFacade().deleteVirtualVault(tempVault);
                        if (success) {
                            JOptionPane.showMessageDialog(dialog, "VirtualVault eliminato con successo!");
                            dialog.dispose();
                            VaultController.load();
                            setUpComboBox();
                        } else {
                            JOptionPane.showMessageDialog(dialog,
                                    "Errore nell'eliminazione del VirtualVault.",
                                    "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }

            });
            dialog.setVisible(true);
        });


        // --------------Listener per il pulsante Deposita--------------
        view.getDepositButton().addActionListener(e -> {
            if (virtualVault == null || virtualVault.getID() == 0) {
                JOptionPane.showMessageDialog(view, "Seleziona un VirtualVault valido prima di depositare.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
            ModifyBalanceDialog dialog = new ModifyBalanceDialog(parentFrame, "Deposita Importo");
            dialog.addConfirmListener(ae -> {
                double amount = dialog.getAmount();
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Aggiorniamo il balance del currentVault
                virtualVault.setBalance(virtualVault.getBalance() + amount);
                virtualVault.setID(virtualVault.getID());
                //System.out.println(virtualVault);



                User loggedUser = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();

                boolean depositSuccess;
                try {
                    // Effettua il prelievo del balance del vault da eliminare nel vault principale
                    depositSuccess = LaVaultFacade.getInstance().getVaultFacade().ritiroVault(loggedUser, amount);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog,
                            "Errore nel reintegrare il saldo nel Vault principale.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!depositSuccess) {
                    JOptionPane.showMessageDialog(dialog,
                            "Impossibile reintegrare il saldo del VirtualVault da eliminare.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //System.out.println("SONO IL VV " + LaVaultFacade.getInstance().getVirtualVaultFacade().getVirtualVault(virtualVault));
                boolean success = LaVaultFacade.getInstance().getVirtualVaultFacade().editVirtualVault(virtualVault);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Deposito effettuato con successo!");
                    dialog.dispose();
                    VaultController.load();
                    setUpComboBox();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Errore nel deposito!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.setVisible(true);
        });

        // --------------Listener per il pulsante Preleva --------------
        view.getWithdrawButton().addActionListener(e -> {
            if (virtualVault == null || virtualVault.getID() == 0) {
                JOptionPane.showMessageDialog(view, "Seleziona un VirtualVault valido prima di prelevare.", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(view);
            ModifyBalanceDialog dialog = new ModifyBalanceDialog(parentFrame, "Preleva Importo");
            dialog.addConfirmListener(ae -> {
                double amount = dialog.getAmount();
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Inserisci un importo valido!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (amount > virtualVault.getBalance()) {
                    JOptionPane.showMessageDialog(dialog, "Importo superiore al saldo disponibile!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Aggiorniamo il balance
                virtualVault.setBalance(virtualVault.getBalance() - amount);

                User loggedUser = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();

                boolean depositSuccess;
                try {
                    // Effettua il prelievo del balance del vault da eliminare nel vault principale
                    depositSuccess = LaVaultFacade.getInstance().getVaultFacade().depositoVault(loggedUser, amount);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog,
                            "Errore nel reintegrare il saldo nel Vault principale.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!depositSuccess) {
                    JOptionPane.showMessageDialog(dialog,
                            "Impossibile reintegrare il saldo del VirtualVault da eliminare.",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = LaVaultFacade.getInstance().getVirtualVaultFacade().editVirtualVault(virtualVault);
                if (success) {
                    JOptionPane.showMessageDialog(dialog, "Prelievo effettuato con successo!");
                    dialog.dispose();
                    VaultController.load();
                    setUpComboBox();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Errore nel prelievo!", "Errore", JOptionPane.ERROR_MESSAGE);
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

}

