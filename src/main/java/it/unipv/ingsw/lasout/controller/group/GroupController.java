package it.unipv.ingsw.lasout.controller.group;

import it.unipv.ingsw.lasout.controller.Loadable;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.group.Debito;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.spesa.Spesa;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.group.GroupItem;
import it.unipv.ingsw.lasout.view.group.GroupPanel;
import it.unipv.ingsw.lasout.view.group.info.ExpenseRowPanel;
import it.unipv.ingsw.lasout.view.group.info.InfoRowPanel;
import it.unipv.ingsw.lasout.view.group.invite.InviteRowPanel;
import it.unipv.ingsw.lasout.view.group.settings.ParticipantRowPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GroupController implements Loadable {
    private static GroupPanel groupPanel;
    private static Group activeGroup;

    public GroupController(GroupPanel groupPanel){
        GroupController.groupPanel = groupPanel;
        initController();
    }


    private void initController() {

        //=============== Selezione menu a tendina ===================
        groupPanel.addComboBoxListener(e -> {
            // Recupera l'oggetto selezionato
            GroupItem selected = groupPanel.getSelectedGroup();

            if (selected != null && selected.getId() != -1) {
                activeGroup = LaVaultFacade.getInstance().getGroupFacade().getGroup(new Group(selected.getId()));
                System.out.println("Controllo id gruppo selezionato: " + selected.getId());
                setUpJInfoPanelLeft();
                setUpJInfoPanelRight();

                groupPanel.setNameLabelText(activeGroup.getName());
                groupPanel.getImpostazioniBtn().setEnabled(true);
                groupPanel.getAggiungiSpesaBtn().setEnabled(true);
                groupPanel.getFinalizzaBtn().setEnabled(true);
                groupPanel.getInvitaBtn().setEnabled(true);
            }
        });


        // ================ Quando clicco "Nuovo Gruppo" =======================
        //mostro jdialog
        groupPanel.addNuovoGruppoListener(e -> {
            groupPanel.getNewGroupDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getNewGroupDialog().setVisible(true);
        });

        // Listener per il bottone "Crea" del NewGroupDialog
        groupPanel.getNewGroupDialog().addCreaListener(e -> {
            try {
                String nomeGruppo = groupPanel.getNewGroupDialog().getNomeGruppoText();
                if (nomeGruppo == null || nomeGruppo.isEmpty() || nomeGruppo.equals("")) throw new Exception();
                System.out.println("Crea nuovo gruppo: " + nomeGruppo);

                List<User> users = new ArrayList<User>();
                users.add(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
                LaVaultFacade.getInstance().getGroupFacade().newGroup(new Group(nomeGruppo, LaVaultFacade.getInstance().getSessionFacade().getLoggedUser(), users));

                groupPanel.getNewGroupDialog().getNomeGruppoField().setText("");
                groupPanel.getNewGroupDialog().dispose();
                load();
            } catch (Exception ex) {
                System.out.println("il nome inserito non e un nome");
                JOptionPane.showMessageDialog(groupPanel, "Inserisci un nome valido", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        //================= Quando clicco "impostazioni"=====================
        groupPanel.addImpostazioniListener(e -> {
            if (activeGroup.isAdmin(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser())) {
                groupPanel.getSettingsDialog().setAmin("l'admin è: (Tu)");
            } else {
                groupPanel.getSettingsDialog().setAmin("l'admin è: " + LaVaultFacade.getInstance().getUserFacade().getUser(activeGroup.getAdmin()).getUsername());
            }
            groupPanel.getSettingsDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getSettingsDialog().setVisible(true);
        });

        // elimina partecipanti
        groupPanel.getSettingsDialog().addEliminateUserListener(e -> {
            setUpRemoveParticipant();
            groupPanel.getSettingsDialog().getRemuveDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getSettingsDialog().getRemuveDialog().setVisible(true);
        });

        // leave groups
        groupPanel.getSettingsDialog().addLeaveGroupListener(e -> {
            if (LaVaultFacade.getInstance().getGroupFacade().leaveGroup(activeGroup)) {
                JOptionPane.showMessageDialog(groupPanel, "Hai abbandonato il gruppo");
                out();
                load();
                groupPanel.getSettingsDialog().dispose();
            } else {
                JOptionPane.showMessageDialog(groupPanel, "Errore nel lasciare il gruppo");
            }
        });

        //elimina gruppo
        groupPanel.getSettingsDialog().addDelateGroupListener(e -> {
            if (LaVaultFacade.getInstance().getGroupFacade().deleteGroup(activeGroup)) {
                out();
                load();
                JOptionPane.showMessageDialog(groupPanel, "il gruppo e stato eliminato");
                groupPanel.getSettingsDialog().dispose();
            } else {
                JOptionPane.showMessageDialog(groupPanel, "Solo l'admin puo eliminare il gruppo");
            }
        });

        // ====================Quando clicco "Invita"==================
        groupPanel.addInvitaListener(e -> {
            setUpInviteFriendList();
            groupPanel.getInviteDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getInviteDialog().setVisible(true);
        });

        //=======================Quando clicco "Aggiungi spesa"=================
        groupPanel.addAggiungiSpesaListener(e -> {
            // Mostra il dialog per aggiungere una spesa
            groupPanel.getAddExpenseDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getAddExpenseDialog().setVisible(true);
        });

        groupPanel.getAddExpenseDialog().addAggiungiListener(e -> {
            // Recupera i valori inseriti nei campi del dialog
            String campo1 = groupPanel.getAddExpenseDialog().getField1Text();
            String campo2 = groupPanel.getAddExpenseDialog().getField2Text();
            try {
                double importo = Double.parseDouble(campo1);
                if (importo == 0) throw new NumberFormatException();
                System.out.println("Aggiungi spesa: " + importo + ", " + campo2);

                Spesa spesa = new Spesa(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser(), activeGroup, importo, campo2);
                LaVaultFacade.getInstance().getGroupFacade().addSpesaToGroup(activeGroup, spesa);

                groupPanel.getAddExpenseDialog().getField1().setText("");
                groupPanel.getAddExpenseDialog().getField2().setText("");
                groupPanel.getAddExpenseDialog().dispose();
            } catch (NumberFormatException ex) {
                System.out.println("Il valore inserito non è numerico");
                JOptionPane.showMessageDialog(groupPanel, "Inserisci un importo numerico valido", "Errore", JOptionPane.ERROR_MESSAGE);
            }
            setUpJInfoPanelLeft();
            setUpJInfoPanelRight();
        });

        // ======================= Quando clicco "Finalizza"=========================
        groupPanel.addFinalizzaListener(e -> {
            if (LaVaultFacade.getInstance().getGroupFacade().finalizzaDebiti(activeGroup)) {
                setUpJInfoPanelLeft();
                setUpJInfoPanelRight();
                JOptionPane.showMessageDialog(groupPanel, "Spese finalizate");
            } else {
                JOptionPane.showMessageDialog(groupPanel, "Per finalizare le spese devi essere admin");
            }
        });
    }

    /**
     * reset e riempimento jinfopanel di destra
     */
    private void setUpJInfoPanelRight() {
        groupPanel.resetJInfoPanelRight();
        groupPanel.getInfoPanel().repaint();
        groupPanel.getInfoPanel().revalidate();
        List<Debito> debiti = LaVaultFacade.getInstance().getGroupFacade().getDebitiFromGroup(activeGroup);
        for (Debito debito : debiti) {
            try {
                if (debito.getDebito() == 0) throw new NumberFormatException();
                User userDebit = LaVaultFacade.getInstance().getUserFacade().getUser(debito.getDebitore());
                User userCredit = LaVaultFacade.getInstance().getUserFacade().getUser(debito.getCreditore());
                InfoRowPanel in = new InfoRowPanel(userDebit.getUsername(), "deve", debito.getDebito() + "€", "a", userCredit.getUsername());
                groupPanel.getInfoPanel().addInfoLine(in);
            } catch (NumberFormatException ignored) {
            }
        }
    }

    /**
     * reset e riempimento jinfopanel di sinistra
     */
    private void setUpJInfoPanelLeft() {
        groupPanel.resetJInfoPanelLeft();
        groupPanel.getInfoPanel().repaint();
        groupPanel.getInfoPanel().revalidate();
        List<Spesa> spese = LaVaultFacade.getInstance().getGroupFacade().getSpeseFromGroup(activeGroup);
        for (Spesa spesa : spese) {
            ExpenseRowPanel ex = new ExpenseRowPanel(LaVaultFacade.getInstance().getUserFacade().getUser(new User(spesa.getEsecutore().getId())).getUsername(), Double.toString(spesa.getAmount()), spesa.getNote());
            expanseRowListener(ex, spesa);
            groupPanel.getInfoPanel().addExpenseLine(ex);
        }
    }

    /**
     * set up e aggiunta azzione al bottone delle rige della spesa
     *
     * @param ex    riga a cui aggiunigre l'azione
     * @param spesa pojo della spesa con l'id di essa
     */
    private void expanseRowListener(ExpenseRowPanel ex, Spesa spesa) {
        ex.addCancListener(e -> {
            LaVaultFacade.getInstance().getGroupFacade().remuveSpesaFromGroup(activeGroup, spesa);
            setUpJInfoPanelLeft();
            setUpJInfoPanelRight();
        });
    }

    /**
     * reset e setup remuvepartecipant
     */
    private void setUpRemoveParticipant() {
        groupPanel.getSettingsDialog().getRemuveDialog().clearRows();
        groupPanel.getSettingsDialog().getRemuveDialog().repaint();
        groupPanel.getSettingsDialog().getRemuveDialog().revalidate();
        List<User> part = LaVaultFacade.getInstance().getGroupFacade().getUserFromGroup(activeGroup);
        for (User user : part) {
            User u = LaVaultFacade.getInstance().getUserFacade().getUser(user);
            if (!activeGroup.isAdmin(u)) {
                ParticipantRowPanel pr = new ParticipantRowPanel(u.getUsername());
                participantRowListener(pr, user);
                groupPanel.getSettingsDialog().getRemuveDialog().addParticipantRow(pr);
            }
        }
    }

    /**
     * set up e aggiunta azzione al bottone delle rige della eliminazione
     *
     * @param pr   riga a cui aggiunigre l'azione
     * @param user pojo con l'id del utente corispondende a quella righa
     */
    private void participantRowListener(ParticipantRowPanel pr, User user) {
        pr.addEspelliListener(e -> {
            if (LaVaultFacade.getInstance().getGroupFacade().removeUserFromGroup(activeGroup, user)) {
                setUpJInfoPanelLeft();
                setUpJInfoPanelRight();
                setUpRemoveParticipant();
                JOptionPane.showMessageDialog(groupPanel, "Utente eliminato dal gruppo");
            } else {
                JOptionPane.showMessageDialog(groupPanel, "Per espellere devi essere admin");
            }
        });
    }

    /**
     * reset e setup lista di amici per l'invito al gruppo (utilizzo del caso d'uso amicizzie)
     */
    private void setUpInviteFriendList() {
        //List<User> friends = LaVaultFacade.getInstance().getFriendFacade().getFriends(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
        List<User> friends = new ArrayList<>();
        friends.add(new User(2));
        friends.add(new User(3));
        friends.add(new User(4));
        friends.add(new User(6));

        for (User user : friends) {
            User us = LaVaultFacade.getInstance().getUserFacade().getUser(user);
            InviteRowPanel in = new InviteRowPanel(us.getUsername());
            inviteRowListener(in, user);
            groupPanel.getInviteDialog().addInviteRow(in);
        }
    }

    /**
     * set up e aggiunta azzione al bottone delle rige della amicizzie
     *
     * @param in   riga a cui aggiunigre l'azione
     * @param user pojo con l'id del utnete di quella riga che in caso verra invitato
     */
    private void inviteRowListener(InviteRowPanel in, User user) {
        in.addActionListener(e -> {
            LaVaultFacade.getInstance().getGroupFacade().invite(activeGroup, user);
        });
    }

    /**
     * caricamento
     */
    public static void load() {
        setUpJComboBox();
    }

    @Override
    public void reload(){
        load();
    }

    /**
     * reset e riempòimento jcombobox
     */
    public static void setUpJComboBox() {
        groupPanel.resetComboBox();
        groupPanel.addGroupItem(new GroupItem(-1, "Seleziona Gruppo..."));
        List<Group> grouplist = LaVaultFacade.getInstance().getUserFacade().getGroupOfLoggedUser();
        for (Group group : grouplist) {
            groupPanel.addGroupItem(new GroupItem((int) group.getId(), group.getName()));
        }
    }

    /**
     * reset della schermata dei gruppi
     */
    public static void out() {
        groupPanel.resetComboBox();
        activeGroup = null;
        groupPanel.getInfoPanel().getLeftPanel().removeAll();
        groupPanel.getInfoPanel().getRightPanel().removeAll();
        groupPanel.getInfoPanel().revalidate();
        groupPanel.getInfoPanel().repaint();
        groupPanel.setNameLabelText("");
        groupPanel.getImpostazioniBtn().setEnabled(false);
        groupPanel.getAggiungiSpesaBtn().setEnabled(false);
        groupPanel.getFinalizzaBtn().setEnabled(false);
        groupPanel.getInvitaBtn().setEnabled(false);

    }
}