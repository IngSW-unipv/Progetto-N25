package it.unipv.ingsw.lasout.view.vault;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import it.unipv.ingsw.lasout.view.mainview.MainUIView;

public class VaultPanel extends JPanel {

	 private JLabel saldoLabel;
	    private JButton aggiornaBtn;

	    public VaultPanel(MainUIView mainUIview) {
	        // Imposta il layout e rendi il pannello non opaco
	        setLayout(new BorderLayout());
	        setOpaque(false);  // Essenziale per non far disegnare un background fisso
	        initComponents();
	    }

	    private void initComponents() {
	        // Crea la label per il saldo, centrata
	        saldoLabel = new JLabel("Saldo: €0.00", SwingConstants.CENTER);
	        saldoLabel.setFont(new Font("Arial", Font.BOLD, 28));
	        saldoLabel.setForeground(Color.BLACK);
	        saldoLabel.setOpaque(false); // Assicurati che la label sia trasparente

	        add(saldoLabel, BorderLayout.CENTER);

	        // Pulsante "Aggiorna Saldo" in basso
	        aggiornaBtn = new JButton("Aggiorna Saldo");
	        aggiornaBtn.setOpaque(false);  // Rendi trasparente il pulsante, per vedere il gradiente sotto
	        add(aggiornaBtn, BorderLayout.SOUTH);
	    }

	    /**
	     * Aggiorna la label del saldo.
	     */
	    public void updateSaldo(double saldo) {
	        saldoLabel.setText("Saldo: €" + String.format("%.2f", saldo));
	    }

	    /**
	     * Permette di aggiungere un listener al pulsante di aggiornamento.
	     */
	    public void addAggiornaListener(ActionListener l) {
	        aggiornaBtn.addActionListener(l);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        // Non chiamiamo super.paintComponent(g) perché abbiamo impostato il pannello non opaco
	        Graphics2D g2d = (Graphics2D) g.create();
	        int w = getWidth();
	        int h = getHeight();

	        // Crea un gradiente verticale, ad esempio da un colore al top a un altro in fondo
	        GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, 0, h, Color.LIGHT_GRAY);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	        g2d.dispose();
	        
	        // Infine, disegna i componenti figli
	        super.paintChildren(g);
	    }
}
