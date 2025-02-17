package it.unipv.ingsw.lasout.controller;


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
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            // Verifica delle credenziali (esempio: admin/1234)
            if ("admin".equalsIgnoreCase(username) && "1234".equals(password)) {
                loginView.setVisible(false);
                showMainUIView();
            } else {
                JOptionPane.showMessageDialog(loginView,
                        "Credenziali errate! Riprova.",
                        "Errore di Login",
                        JOptionPane.ERROR_MESSAGE);
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
            String username = registerView.getUsername();
            String password = registerView.getPassword();
            String email = registerView.getEmail();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(registerView,
                        "Tutti i campi sono obbligatori!",
                        "Errore di Registrazione",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Simulazione registrazione
                JOptionPane.showMessageDialog(registerView,
                        "Registrazione completata! Ora puoi effettuare il login.",
                        "Registrazione Completata",
                        JOptionPane.INFORMATION_MESSAGE);
                registerView.dispose();
                loginView.setVisible(true);
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
