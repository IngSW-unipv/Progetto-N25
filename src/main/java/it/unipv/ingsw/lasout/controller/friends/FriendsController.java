package it.unipv.ingsw.lasout.controller.friends;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.friends.AmicoRowPanel;
import it.unipv.ingsw.lasout.view.friends.AddFriendDialog;
import it.unipv.ingsw.lasout.view.friends.FriendsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendsController {

    private FriendsPanel friendsPanel;

    public FriendsController(FriendsPanel friendsPanel) {
        this.friendsPanel = friendsPanel;
        addListeners();
    }

    private void addListeners() {
        // Quando si clicca sul bottone "Aggiugni Amico"
        friendsPanel.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Otteniamo il frame padre dal FriendsPanel
                Frame parent = (Frame) SwingUtilities.getWindowAncestor(friendsPanel);
                // Creiamo il dialog
                AddFriendDialog dialog = new AddFriendDialog(parent);

                // Aggiungiamo un listener al bottone "Aggiungi" del dialog
                dialog.getAggiungiButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        // Leggiamo il testo inserito nel campo
                        String friendUsername = dialog.getUsername();
                        // Esegui l'azione desiderata (qui stampiamo il testo)
                        System.out.println("Username Amico inserito: " + friendUsername);
                        // Qui potresti aggiungere la logica per salvare o aggiungere l'amico
                        setUpListPanel();
                        dialog.dispose();
                    }
                });

                dialog.setVisible(true);
            }
        });
    }

    public void load() {
        setUpListPanel();
    }

    public void setUpListPanel() {
        List<User> friends = LaVaultFacade.getInstance()
                .getUserFacade()
                .getUser(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser())
                .getFriends();
        for (User u : friends) {
            AmicoRowPanel amico = new AmicoRowPanel(u.getUsername());
            friendsPanel.getListPanel().addRow(amico);
            addListenerToRow(amico, u);
        }
    }

    public void addListenerToRow(AmicoRowPanel amico, User u) {
        amico.addBtnLis(e->{
            //logica per eliminare dalle amicizie del uitente l'oggato l'utente suo amico u
            setUpListPanel();
        });
    }
}
