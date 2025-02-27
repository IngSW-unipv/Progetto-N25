package it.unipv.ingsw.lasout.controller.friends;

import it.unipv.ingsw.lasout.controller.Loadable;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.notify.INotifyFacade;
import it.unipv.ingsw.lasout.facade.user.ISessionFacade;
import it.unipv.ingsw.lasout.facade.user.IUserFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.friends.AmicoRowPanel;
import it.unipv.ingsw.lasout.view.friends.AddFriendDialog;
import it.unipv.ingsw.lasout.view.friends.FriendsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FriendsController implements Loadable {


    private IUserFacade userFacade;
    private ISessionFacade sessionFacade;
    private INotifyFacade iNotifyFacade;

    private FriendsPanel friendsPanel;

    public FriendsController(FriendsPanel friendsPanel){
        this.friendsPanel = friendsPanel;


        userFacade = LaVaultFacade.getInstance().getUserFacade();
        sessionFacade = LaVaultFacade.getInstance().getSessionFacade();
        iNotifyFacade = LaVaultFacade.getInstance().getNotifyFacade();
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

                        String friendUsername = dialog.getUsername();

                        User from = sessionFacade.getLoggedUser();
                        User userName = new User();
                        userName.setUsername(friendUsername);
                        User user = userFacade.getUserByName(userName);

                        iNotifyFacade.sendFriendRequest(from, user);

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
        User  user = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();


        List<User> friends = LaVaultFacade.getInstance().getUserFacade().getFriends(user);

        friendsPanel.getListPanel().clear();


        for (User u : friends) {
            AmicoRowPanel amico = new AmicoRowPanel(u.getUsername());
            friendsPanel.getListPanel().addRow(amico);
            addListenerToRow(amico, u);
        }
    }

    public void addListenerToRow(AmicoRowPanel amico, User u) {
        amico.addBtnLis(e->{

            User of = sessionFacade.getLoggedUser();
            of.getFriends().remove(u);
            u.getFriends().remove(of);
            userFacade.deleteFriendShip(u, of);
            load();
        });
    }

    @Override
    public void reload() {
        load();
    }
}
