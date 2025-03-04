package it.unipv.ingsw.lasout.controller;


import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.view.account.LoginView;
import it.unipv.ingsw.lasout.view.account.SignInView;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class AppController {
    private LoginView loginView;
    private SignInView signInView;
    private MainUIView mainUIView;

    private Set<Loadable> loadables;

    public AppController() {
        loadables = new HashSet<>();
        loginView = new LoginView();
        mainUIView = new MainUIView(this); // Passa "this" per fornire il riferimento all'AppController

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
        //almeno quando riapro la schermata pk ho fatto un logout o eliminato l'account i textField sono vuoti
        loginView.clearFields();
    }

    public void showMainUIView() {
        mainUIView.setVisible(true);
    }

    public void showRegisterView() {
        if (signInView == null) {
            signInView = new SignInView();
            // Aggiungiamo i listener alla view di registrazione
            signInView.addRegisterListener(new RegisterListener());
            signInView.addLoginListener(new BackToLoginListener());
            loginView.clearFields();
        }
        signInView.setVisible(true);
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

            //chiamo la facade per il login
            LaVaultFacade.getInstance().getSessionFacade().login(userCarrier);

            //controllo che i campi non siano vuoti alla pressione del bottone
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(signInView, "Tutti i campi sono obbligatori!", "Errore di Login", JOptionPane.ERROR_MESSAGE);
            } else if (LaVaultFacade.getInstance().getSessionFacade().isLogged()) {
                // Se l'utente è loggato, nascondo la finestra di login e mostro la schermata principale (MainView)
                loginView.setVisible(false);
                loadables.forEach(Loadable::reload);
                showMainUIView();
            } else {
                //se non è riuscito a loggarsi perché le credenziali non sono valide, mostro l’errore
                JOptionPane.showMessageDialog(loginView, "Credenziali errate! Riprova", "Errore di Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void subscribe(Loadable loadable) {
        loadables.add(loadable);
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
            String username = signInView.getUsername();
            String password = signInView.getPassword();
            String email = signInView.getEmail();

            //utilizzo un utente fittizio da passare poi alla facade per la ricerca nel DB
            User userCarrier = new User();
            userCarrier.setUsername(username);
            userCarrier.setEmail(email);
            userCarrier.setPassword(password);

            //chiamo la facade per vedere se l'utente non c'è almeno posso creargli l'account
            LaVaultFacade.getInstance().getSessionFacade().login(userCarrier);

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(signInView, "Tutti i campi sono obbligatori!", "Errore di Registrazione", JOptionPane.ERROR_MESSAGE);
            } else if (!LaVaultFacade.getInstance().getSessionFacade().isLogged()) {
                LaVaultFacade.getInstance().getUserFacade().createAccount(userCarrier);
                JOptionPane.showMessageDialog(signInView, "Registrazione completata! Ora puoi effettuare il login", "Registrazione Completata", JOptionPane.INFORMATION_MESSAGE);
                signInView.dispose();
                loginView.setVisible(true);
                signInView.clearFields();
            } else {
                JOptionPane.showMessageDialog(signInView, "User already exist, impossible to CREATE your account. Please use another username", "Errore di Registrazione", JOptionPane.ERROR_MESSAGE);
                signInView.clearFields();
            }
        }
    }

    // Listener per tornare alla schermata di login dalla registrazione
    class BackToLoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            signInView.dispose();
            loginView.setVisible(true);
        }
    }
}
