package it.unipv.ingsw.lasout.controller;


import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.LoginView;
import it.unipv.ingsw.lasout.view.RegisterView;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppController {
    private LoginView loginView;
    private RegisterView registerView;
    private MainUIView mainUIView;

    public AppController() {
        // Inizializziamo le view
        loginView = new LoginView();
        mainUIView = new MainUIView();

        // Aggiungiamo i listener alla view di login
        loginView.addLoginListener(new LoginListener());
        loginView.addRegisterListener(new OpenRegisterListener());

        // Aggiungiamo i listener ai bottoni di navigazione della MainUI
        String[] buttonLabels = mainUIView.getButtonLabels();
        for (int i = 0; i < mainUIView.getNavButtons().length; i++) {
            final String cardName = buttonLabels[i];
            mainUIView.getNavButtons()[i].addActionListener(e -> mainUIView.showCard(cardName));
        }
    }



    public void showLoginView() {
        loginView.setVisible(true);
    }

    public void showMainUIView() {
        mainUIView.setVisible(true);
    }

    public void showRegisterView() {
        if (registerView == null) {
            registerView = new RegisterView();
            // Aggiungiamo i listener alla view di registrazione
            registerView.addRegisterListener(new RegisterListener());
            registerView.addLoginListener(new BackToLoginListener());
        }
        registerView.setVisible(true);
    }

    // Listener per il login
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //prendo dalle JtextField le credenziali per il login
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            //utilizzo un utente fittizio da passare poi alla facade per la ricerca nel DB
            User userCarrier = new User();
            userCarrier.setUsername(username);
            userCarrier.setPassword(password);

            loginView.preSetDada();

            //chiamo la facade per il login
            LaVaultFacade.getInstance().getSessionFacade().login(userCarrier);

            //controllo che i campi non siano vuoti alla pressione del bottone
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(registerView,"Tutti i campi sono obbligatori!","Errore di Login",JOptionPane.ERROR_MESSAGE);
            }else if (LaVaultFacade.getInstance().getSessionFacade().isLogged()) {
                // Se l'utente è loggato, nascondo la finestra di login e mostro la schermata principale (MainView)
                loginView.setVisible(false);
                showMainUIView();
            } else {
                //se non è riuscito a loggarsi perché le credenziali non sono valide, mostro l’errore
                JOptionPane.showMessageDialog(loginView,"Credenziali errate! Riprova","Errore di Login",JOptionPane.ERROR_MESSAGE);
                //loginView.clearFields();
            }
        }
    }

    // Listener per aprire la schermata di registrazione
    class OpenRegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.setVisible(false);
            showRegisterView();
        }
    }

    // Listener per la registrazione
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //prendo dalle JtextField le credenziali per la registrazione
            String username = registerView.getUsername();
            String password = registerView.getPassword();
            String email = registerView.getEmail();

            //utilizzo un utente fittizio da passare poi alla facade per la ricerca nel DB
            User userCarrier = new User();
            userCarrier.setUsername(username);
            userCarrier.setEmail(email);
            userCarrier.setPassword(password);

            //chiamo la facade per vedere se l'utente non c'è almeno posso creargli l'account
            LaVaultFacade.getInstance().getSessionFacade().login(userCarrier);

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(registerView,
                        "Tutti i campi sono obbligatori!",
                        "Errore di Registrazione",
                        JOptionPane.ERROR_MESSAGE);
            } else if(!LaVaultFacade.getInstance().getSessionFacade().isLogged()) {
                LaVaultFacade.getInstance().getUserFacade().createAccount(userCarrier);
                JOptionPane.showMessageDialog(registerView,"Registrazione completata! Ora puoi effettuare il login","Registrazione Completata",JOptionPane.INFORMATION_MESSAGE);
                registerView.dispose();
                loginView.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(registerView,"User already exist, impossible to CREATE your account. Please use another username","Errore di Registrazione",JOptionPane.ERROR_MESSAGE);
                registerView.clearFields();
            }
        }
    }

    // Listener per tornare alla schermata di login dalla registrazione
    class BackToLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerView.dispose();
            loginView.setVisible(true);
        }
    }
}
