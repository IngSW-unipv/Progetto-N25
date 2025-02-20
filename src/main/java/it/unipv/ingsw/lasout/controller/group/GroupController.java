package it.unipv.ingsw.lasout.controller.group;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.group.spesa.Spesa;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.group.ExpenseRowPanel;
import it.unipv.ingsw.lasout.view.group.GroupItem;
import it.unipv.ingsw.lasout.view.group.GroupPanel;
import it.unipv.ingsw.lasout.view.group.InviteRowPanel;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class GroupController {
    private static GroupPanel groupPanel;
    private static Group activeGroup;

    public GroupController(GroupPanel groupPanel) {
        this.groupPanel = groupPanel;
        initController();
    }

    private void initController() {

        //=============== Quando scelgo nel menu a tendina ===================
        groupPanel.addComboBoxListener(e -> {
            // Recupera l'oggetto selezionato nel jcombobox
            GroupItem selected = (GroupItem) groupPanel.getSelectedGroup();

            if (selected != null && selected.getId() != -1) {
                activeGroup= LaVaultFacade.getInstance().getGroupFacade().getGroup(new Group(selected.getId()));
                System.out.println("Controllo id gruppo selezionato: "+selected.getId());
                setUpJInfoPanel();

                groupPanel.setNameLabelText(activeGroup.getName());
                groupPanel.getImpostazioniBtn().setEnabled(true);
                groupPanel.getAggiungiSpesaBtn().setEnabled(true);
                groupPanel.getFinalizzaBtn().setEnabled(true);
                groupPanel.getInvitaBtn().setEnabled(true);
            }
        });


        // ================ Quando clicco "Nuovo Gruppo" =======================
        groupPanel.addNuovoGruppoListener(e -> {
            groupPanel.getNewGroupDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getNewGroupDialog().setVisible(true);
        });

        // Listener per il bottone "Crea" del NewGroupDialog
        groupPanel.getNewGroupDialog().addCreaListener(e -> {
            try{
                String nomeGruppo = groupPanel.getNewGroupDialog().getNomeGruppoText();
                if(nomeGruppo==null||nomeGruppo.isEmpty()||nomeGruppo.equals("")) throw new Exception();
                System.out.println("Crea nuovo gruppo: " + nomeGruppo);

                List<User> users = new ArrayList<User>();
                users.add(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
                LaVaultFacade.getInstance().getGroupFacade().newGroup(new Group(nomeGruppo,LaVaultFacade.getInstance().getSessionFacade().getLoggedUser(),users));

                groupPanel.getNewGroupDialog().getNomeGruppoField().setText("");
                groupPanel.getNewGroupDialog().dispose();
                load();
            }catch(Exception ex){
                System.out.println("il nome inserito non e un nome");
                JOptionPane.showMessageDialog(groupPanel, "Inserisci un nome valido", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        //================= Quando clicco "impostaz"=====================
        groupPanel.addImpostazioniListener(e -> {
            groupPanel.getSettingsDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getSettingsDialog().setVisible(true);
        });

        groupPanel.getSettingsDialog().addEliminateUserListener(e -> JOptionPane.showMessageDialog(groupPanel, "Hai premuto Opzione 1"));
        // leave groups
        groupPanel.getSettingsDialog().addLeaveGroupListener(e -> {
            if(LaVaultFacade.getInstance().getGroupFacade().leaveGroup(activeGroup)){
                JOptionPane.showMessageDialog(groupPanel, "Hai abbandonato il gruppo");
                out();
                load();
                groupPanel.getSettingsDialog().dispose();
            }else{
                JOptionPane.showMessageDialog(groupPanel, "Errore nel lasciare il gruppo");
            }
        });
        groupPanel.getSettingsDialog().addDelateGroupListener(e -> {
            if(LaVaultFacade.getInstance().getGroupFacade().deleteGroup(activeGroup)) {
                out();
                load();
                JOptionPane.showMessageDialog(groupPanel, "il gruppo e stato eliminato");
                groupPanel.getSettingsDialog().dispose();
            }else{
                JOptionPane.showMessageDialog(groupPanel, "Solo l'admin puo eliminare il gruppo");
            }
        });

        // ====================Quando clicco "Invita"==================
        groupPanel.addInvitaListener(e -> {
            inviteFriendList();
            groupPanel.getInviteDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getInviteDialog().setVisible(true);
        });

        //=======================Quando clicco "Aggiungi spesa"=================
        groupPanel.addAggiungiSpesaListener(e -> {
            // Mostra il dialog per aggiungere una spesa
            groupPanel.getAddExpenseDialog().setLocationRelativeTo(groupPanel);
            groupPanel.getAddExpenseDialog().setVisible(true);
        });

        groupPanel.getAddExpenseDialog().addAggiungiListener(e->{
            // Recupera i valori inseriti nei campi del dialog
            String campo1 = groupPanel.getAddExpenseDialog().getField1Text();
            String campo2 = groupPanel.getAddExpenseDialog().getField2Text();
            try {
                double importo = Double.parseDouble(campo1);
                if(importo==0) throw new NumberFormatException();
                System.out.println("Aggiungi spesa: " + importo + ", " + campo2);

                Spesa spesa= new Spesa(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser(),activeGroup,importo,campo2);
                LaVaultFacade.getInstance().getGroupFacade().addSpesaToGroup(activeGroup,spesa);

                groupPanel.getAddExpenseDialog().getField1().setText("");
                groupPanel.getAddExpenseDialog().getField2().setText("");
                groupPanel.getAddExpenseDialog().dispose();
            } catch (NumberFormatException ex) {
                System.out.println("Il valore inserito non è numerico");
                JOptionPane.showMessageDialog(groupPanel, "Inserisci un importo numerico valido", "Errore", JOptionPane.ERROR_MESSAGE);
            }
            setUpJInfoPanel();
        });



        // ======================= Quando clicco "Finalizza"=========================
        groupPanel.addFinalizzaListener(e ->{
                if(LaVaultFacade.getInstance().getGroupFacade().finalizzaDebiti(activeGroup)){
                    setUpJInfoPanel();
                    JOptionPane.showMessageDialog(groupPanel, "Spese finalizate");
                }else{
                    JOptionPane.showMessageDialog(groupPanel, "Per finalizare le spese devi essere admin");
                }
        });
    }

    public static void load(){
        setUpJComboBox();
    }

    public static void setUpJComboBox(){
        groupPanel.resetComboBox();
        groupPanel.addGroupItem(new GroupItem(-1, "Seleziona Gruppo..."));
        List<Group> grouplist= LaVaultFacade.getInstance().getUserFacade().getGroupOfLoggedUser();
        for(Group group:grouplist){
            groupPanel.addGroupItem(new GroupItem((int) group.getId(), group.getName()));
        }
    }

    public void setUpJInfoPanel(){
        groupPanel.resetJInfoPanelLeft();
        groupPanel.getInfoPanel().repaint();
        groupPanel.getInfoPanel().revalidate();
        List<Spesa> spese = LaVaultFacade.getInstance().getGroupFacade().getSpeseFromGroup(activeGroup);
        for(Spesa spesa:spese){
            ExpenseRowPanel ex = new ExpenseRowPanel(LaVaultFacade.getInstance().getUserFacade().getUser(new User(spesa.getEsecutore().getId())).getUsername(),Double.toString(spesa.getAmount()),spesa.getNote());
            expnseRowListener(ex,spesa);
            groupPanel.getInfoPanel().addExpenseLine(ex);
        }
    }

    public void expnseRowListener(ExpenseRowPanel ex, Spesa spesa){
        ex.addCancListener(e->{
            LaVaultFacade.getInstance().getGroupFacade().remuveSpesaFromGroup(activeGroup,spesa);
            setUpJInfoPanel();
        });
    }

    public void inviteFriendList(){
        //List<User> friends = LaVaultFacade.getInstance().getFriendFacade().getFriends(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser());
        List<User> friends = new ArrayList<>();
        friends.add(new User(2));
        friends.add(new User(3));
        friends.add(new User(4));
        friends.add(new User(6));

        for(User user:friends){
            User us =LaVaultFacade.getInstance().getUserFacade().getUser(user);
            InviteRowPanel in = new InviteRowPanel(us.getUsername());
            inviteRowListener(in,user);
            groupPanel.getInviteDialog().addInviteRow(in);
        }

    }

    public void inviteRowListener(InviteRowPanel in, User user){
        in.addActionListener(e->{
            LaVaultFacade.getInstance().getGroupFacade().invite(activeGroup,user);
        });
    }

    public static void out(){
        groupPanel.resetComboBox();
        activeGroup=null;
        groupPanel.getInfoPanel().getLeftPanel().removeAll();
        groupPanel.getInfoPanel().getRightPanel().removeAll();
        groupPanel.getInfoPanel().revalidate();
        groupPanel.getInfoPanel().repaint();
        groupPanel.setNameLabelText("");
        groupPanel.getImpostazioniBtn().setEnabled(false);
        groupPanel.getAggiungiSpesaBtn().setEnabled(false);
        groupPanel.getFinalizzaBtn().setEnabled(false);
    }
}