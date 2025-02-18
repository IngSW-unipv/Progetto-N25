package it.unipv.ingsw.lasout.controller.account;

import it.unipv.ingsw.lasout.controller.AppController;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.LoginView;
import it.unipv.ingsw.lasout.view.account.AccountPanel;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountController {

    private AccountPanel accountPanel;
    private AppController appController;


    public AccountController(AccountPanel accountPanel, AppController appController) {
        this.accountPanel = accountPanel;
        this.appController = appController;


        // Aggiungiamo i listener
        accountPanel.addChangePasswordListener(new ChangePasswordButtonListener());
        accountPanel.addDeleteAccountListener(new DeleteAccountBottonListener());
        accountPanel.addLogoutListener(new LogOutButtonListener());
    }



    // Listener per il primo bottone
    class ChangePasswordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String oldPassword = accountPanel.getOldPassword();
            String newPassword = accountPanel.getNewPassword();
            String repeatPassword = accountPanel.getNewPassword();
            User user = new User();
            user = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();

            if (oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty()) {
                JOptionPane.showMessageDialog(accountPanel,"Tutti i campi sono obbligatori!","Errore nel cambio della password",JOptionPane.ERROR_MESSAGE);
            // controllo che la vecchia password sia effettivamente quella che aveva prima
            }else if(!oldPassword.equals(user.getPassword())) {
                JOptionPane.showMessageDialog(accountPanel,"La vecchia password non è uguale a quella inserita","Errore nel cambio della password",JOptionPane.ERROR_MESSAGE);
            // controllo che le 2 password (nuova e ripetuta) siano identiche
            }else if(newPassword.equals(repeatPassword)) {
                LaVaultFacade.getInstance().getUserFacade().updatePassword(user, newPassword);
                JOptionPane.showMessageDialog(accountPanel,"Password cambiata con successo","Conferma di cambio password",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Listener per il secondo bottone
    class DeleteAccountBottonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
            LaVaultFacade.getInstance().getUserFacade().deleteAccount(user);

            JOptionPane.showMessageDialog(null,"Account eliminato con successo, a breve sarai riportato alla schermata di login","Rimozione account",JOptionPane.INFORMATION_MESSAGE);

            //dopo aver fatto il logout verrò riportato alla schermata del login
            // Chiude la finestra principale
            SwingUtilities.getWindowAncestor(accountPanel).dispose();
            // Utilizza il metodo di AppController per mostrare la schermata di login
            appController.showLoginView();
        }
    }

    // Listener per il secondo bottone
    class LogOutButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //User user = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
            LaVaultFacade.getInstance().getSessionFacade().logout();

            JOptionPane.showMessageDialog(null,"Logout effettuato con successo, a breve sarai riportato alla schermata di login","Logout",JOptionPane.INFORMATION_MESSAGE);

            //dopo aver fatto il logout verrò riportato alla schermata del login
            // Chiude la finestra principale
            SwingUtilities.getWindowAncestor(accountPanel).dispose();
            // Utilizza il metodo di AppController per mostrare la schermata di login
            appController.showLoginView();
        }
    }
}
