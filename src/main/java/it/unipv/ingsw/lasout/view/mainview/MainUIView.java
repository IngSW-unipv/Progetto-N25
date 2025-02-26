package it.unipv.ingsw.lasout.view.mainview;


import it.unipv.ingsw.lasout.controller.AppController;
import it.unipv.ingsw.lasout.controller.account.AccountController;
import it.unipv.ingsw.lasout.controller.cashbook.CashbookController;
import it.unipv.ingsw.lasout.controller.group.GroupController;
import it.unipv.ingsw.lasout.controller.vault.VaultController;
import it.unipv.ingsw.lasout.controller.vault.VaultController;
import it.unipv.ingsw.lasout.controller.virtualVault.VirtualVaultController;
import it.unipv.ingsw.lasout.view.account.AccountPanel;
import it.unipv.ingsw.lasout.view.cashbook.CashbookPanel;
import it.unipv.ingsw.lasout.view.group.GroupPanel;
import it.unipv.ingsw.lasout.view.vault.VaultPanel;
import it.unipv.ingsw.lasout.view.vault.VaultPanel;
import it.unipv.ingsw.lasout.view.virtualVault.VirtualVaultPanel;

import javax.swing.*;
import java.awt.*;

public class MainUIView extends JFrame {


    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel leftPanel;
    private JButton[] navButtons;
    private String[] buttonLabels = {
            "vault", "virtualvault", "Group", "cashbook",
            "notifies", "friends", "account"
    };

    public MainUIView(AppController appController) {
        setTitle("Finestra Principale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 660);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainUIView.class.getResource("/view/logo.png")));

        // Pannello sinistro con i bottoni di navigazione
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(7, 1, 10, 10));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        navButtons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            navButtons[i] = new JButton(buttonLabels[i]);
            navButtons[i].setFont(new Font("Arial", Font.BOLD, 14));
            navButtons[i].setFocusPainted(false);
            navButtons[i].setMargin(new Insets(10, 20, 10, 20));
            leftPanel.add(navButtons[i]);
        }

        // Pannello destro con CardLayout e bordo bianco spesso
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));

        // Aggiungiamo le "card" al pannello
        VaultPanel vaultPanel = new VaultPanel(this);
        new VaultController(vaultPanel);
        contentPanel.add(vaultPanel, "vault");


        //Aggiunta pannello virtualVault
        VirtualVaultPanel virtualVaultPanel = new VirtualVaultPanel(this);
        new VirtualVaultController(virtualVaultPanel);
        contentPanel.add(virtualVaultPanel, "virtualvault");
        //contentPanel.add(createCard("Contenuto: VirtualVault", new Color(160, 82, 45)), "virtualVault");

        // Aggiungiamo il pannello "Gruppi"
        GroupPanel groupPanel = new GroupPanel(this);
        new GroupController(groupPanel);
        contentPanel.add(groupPanel, "Group");
        contentPanel.add(createCard("Contenuto: Notifies", new Color(150, 75, 0)), "notifies");
        contentPanel.add(createCard("Contenuto: Friends", new Color(205, 92, 92)), "friends");

        // Creazione e aggiunta AccountPanel
        AccountPanel accountPanel = new AccountPanel(this);
        new AccountController(accountPanel, appController);
        contentPanel.add(accountPanel, "account");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Aggiunta CashbookPanel
        CashbookPanel cashbookPanel = new CashbookPanel();
        new CashbookController(cashbookPanel); // Passiamo l'utente
        contentPanel.add(cashbookPanel, "cashbook");
    }

    private JPanel createCard(String text, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.add(new JLabel(text));
        return panel;
    }

    public void showCard(String cardName) {
        cardLayout.show(contentPanel, cardName);
    }

    public JButton[] getNavButtons() {
        return navButtons;
    }

    public String[] getButtonLabels() {
        return buttonLabels;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        GroupController.load();
        VaultController.load();
        VirtualVaultController.load();
        CashbookController.load();
    }
}
