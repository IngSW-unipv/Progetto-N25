package it.unipv.ingsw.lasout.view.vault;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;

public class VaultPanel extends JPanel {

	private JLabel saldoLabel;
    // Aggiungi un pulsante per aprire il dialogo di aggiunta metodo
    private JButton aggiungiMetodoBtn;
    private JList<String> paymentMethodsList;
    private JList<String> transactionsList;
    private JButton depositBtn;
    private JButton withdrawBtn;
    
    public VaultPanel(MainUIView mainUIview) {
        setLayout(new BorderLayout(10,10));
        initComponents();
    }
    
    private void initComponents() {
        // Pannello superiore: qui inseriamo il pulsante "Aggiungi Metodo di Pagamento"
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        
        // Puoi mettere qui eventuali altri componenti (come il menu già esistente)
        // e posizionare il pulsante a destra.
        aggiungiMetodoBtn = new JButton("Aggiungi Metodo di Pagamento");
        // Imposta eventualmente lo stile o la trasparenza
        aggiungiMetodoBtn.setOpaque(false);
        topPanel.add(aggiungiMetodoBtn, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        
        // CENTER: Saldo
        saldoLabel = new JLabel("Saldo: €0.00", SwingConstants.CENTER);
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 28));
        saldoLabel.setForeground(Color.BLACK);
        add(saldoLabel, BorderLayout.CENTER);
        
        // EAST: Lista dei metodi di pagamento
        JPanel eastPanel = new JPanel(new BorderLayout());
        eastPanel.setPreferredSize(new Dimension(200, getHeight()));
        eastPanel.add(new JLabel("Metodi di Pagamento", SwingConstants.CENTER), BorderLayout.NORTH);
        paymentMethodsList = new JList<>(new DefaultListModel<>());
        eastPanel.add(new JScrollPane(paymentMethodsList), BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        
        // WEST: Lista delle transazioni
        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(200, getHeight()));
        westPanel.add(new JLabel("Transazioni", SwingConstants.CENTER), BorderLayout.NORTH);
        transactionsList = new JList<>(new DefaultListModel<>());
        westPanel.add(new JScrollPane(transactionsList), BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
        
     // SOUTH: Pannello per i due nuovi pulsanti "Deposit" e "Withdraw"
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        depositBtn = new JButton("Deposita Denaro");
        withdrawBtn = new JButton("Preleva Denaro");
        depositBtn.setPreferredSize(new Dimension(150, 50));
        withdrawBtn.setPreferredSize(new Dimension(150, 50));
        southPanel.add(depositBtn);
        southPanel.add(withdrawBtn);
        add(southPanel, BorderLayout.SOUTH);
    }
    
    // Metodi getter e setter esistenti...
    public void updateSaldo(double saldo) {
        saldoLabel.setText("Saldo: €" + String.format("%.2f", saldo));
    }
    
    public void addAggiornaListener(ActionListener l) {
    }
    
    // Nuovo metodo getter per il pulsante di aggiunta
    public void addAggiungiMetodoListener(ActionListener l) {
        aggiungiMetodoBtn.addActionListener(l);
    }
    
    public JList<String> getPaymentMethodsList() {
        return paymentMethodsList;
    }
    
    public void updatePaymentMethodsList(DefaultListModel<String> model) {
        paymentMethodsList.setModel(model);
    }
    
    public void addDepositListener(ActionListener l) {
        depositBtn.addActionListener(l);
    }
    
    public void addWithdrawListener(ActionListener l) {
        withdrawBtn.addActionListener(l);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int w = getWidth();
        int h = getHeight();
        // Esempio: sfumatura da blu chiaro a blu scuro
        GradientPaint gp = new GradientPaint(0, 0, new Color(173,216,230), 0, h, new Color(0,0,139));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        super.paintChildren(g);
    }
}
