package it.unipv.ingsw.lasout.view.mainview;


import it.unipv.ingsw.lasout.controller.AppController;
import it.unipv.ingsw.lasout.controller.account.AccountController;
import it.unipv.ingsw.lasout.controller.cashbook.CashbookController;
import it.unipv.ingsw.lasout.controller.friends.FriendsController;
import it.unipv.ingsw.lasout.controller.group.GroupController;
import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.controller.vault.VaultController;
import it.unipv.ingsw.lasout.controller.vault.VaultController;
import it.unipv.ingsw.lasout.controller.virtualVault.VirtualVaultController;
import it.unipv.ingsw.lasout.view.LaColor;
import it.unipv.ingsw.lasout.view.account.AccountPanel;
import it.unipv.ingsw.lasout.view.account.LoginView;
import it.unipv.ingsw.lasout.view.cashbook.CashbookPanel;
import it.unipv.ingsw.lasout.view.friends.FriendsPanel;
import it.unipv.ingsw.lasout.view.group.GroupPanel;
import it.unipv.ingsw.lasout.view.notify.NotifyPanel;
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
            "vault", "virtualvault", "Group", "Cashbook",
            "notifies", "friends", "account"
    };

    private GroupController groupController;
    private NotifyController notifyController;
    private FriendsController friendsController;

    public MainUIView(AppController appController) {

        prepareMainView();
        prepareLeftPanel();
        prepareButtons();
        prepareCardLayout();

        // Creazione e aggiunta AccountPanel
        AccountPanel accountPanel = new AccountPanel(this);
        new AccountController(accountPanel, appController);
        contentPanel.add(accountPanel, "account");

        prepareUseCases(appController);
        addContents();

    }

    private void prepareMainView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainUIView.class.getResource("/view/logo.png")));
        setTitle("Finestra Principale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 660);
        setLocationRelativeTo(null);
    }

    private void prepareUseCases(AppController  controller) {
        // Aggiungiamo le "card" al pannello
        VaultPanel vaultPanel = new VaultPanel(this);
        VaultController vaultController = new VaultController(vaultPanel);
        contentPanel.add(vaultPanel, "vault");


        //Aggiunta pannello virtualVault
        VirtualVaultPanel virtualVaultPanel = new VirtualVaultPanel(this);
        new VirtualVaultController(virtualVaultPanel);
        contentPanel.add(virtualVaultPanel, "virtualvault");
        //contentPanel.add(createCard("Contenuto: VirtualVault", new Color(160, 82, 45)), "virtualVault");

        // Aggiunta il pannello "Gruppi"
        GroupPanel groupPanel = new GroupPanel(this);
        groupController = new GroupController(groupPanel);

        contentPanel.add(groupPanel, "Group");

        notifyController= new NotifyController();
        NotifyPanel  notifyPanel0 = new NotifyPanel(notifyController);
        contentPanel.add(notifyPanel0, "notifies");


        //contentPanel.add(createCard("Contenuto: Friends", new Color(205, 92, 92)), "friends");
        FriendsPanel friendsPanel = new FriendsPanel();
        friendsController = new FriendsController(friendsPanel);
        contentPanel.add(friendsPanel, "friends");

        // Aggiunta CashbookPanel
        CashbookPanel cashbookPanel = new CashbookPanel();
        new CashbookController(cashbookPanel); // Passiamo l'utente
        contentPanel.add(cashbookPanel, "Cashbook");

        notifyController.subscribe(groupController);
        notifyController.subscribe(vaultController);
        notifyController.subscribe(notifyPanel0);
        notifyController.subscribe(friendsController);
        groupController.subscribe(notifyPanel0);

        controller.subscribe(notifyPanel0);

    }

    public void prepareButtons(){

        navButtons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            navButtons[i] = new JButton(buttonLabels[i]);
            navButtons[i].setFont(new Font("Arial", Font.BOLD, 14));
            navButtons[i].setFocusPainted(false);
            navButtons[i].setBackground(LaColor.BTN_SFONDO);
            navButtons[i].setForeground(LaColor.FONT);
            navButtons[i].setMargin(new Insets(10, 20, 10, 20));
            leftPanel.add(navButtons[i]);
        }
    }

    public  void prepareLeftPanel(){
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(7, 1, 10, 10));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setBackground(LaColor.SFONDO);
    }

    public  void prepareCardLayout(){
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBorder(BorderFactory.createLineBorder(LaColor.SFONDO_SCURO, 8));
    }

    public void addContents(){
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
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
        friendsController.load();
        notifyController.load();
    }
}
