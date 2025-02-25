package it.unipv.ingsw.lasout.controller.vault;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.facade.vault.ConcreteVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.vault.VaultDAO;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.CreditCard;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.CurrentAccount;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PayPal;
import it.unipv.ingsw.lasout.model.vault.paymentmethod.PaymentMethod;
import it.unipv.ingsw.lasout.view.vault.AddPaymentMethodDialog;
import it.unipv.ingsw.lasout.view.vault.DeletePaymentMethodDialog;
import it.unipv.ingsw.lasout.view.vault.PaymentExecutionDialog;
import it.unipv.ingsw.lasout.view.vault.PaymentMethodTransactionDialog;
import it.unipv.ingsw.lasout.view.vault.VaultPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class VaultController {

	private static Vault vault;
	private static VaultPanel vaultPanel;

	public VaultController(VaultPanel vaultPanel) {
		this.vaultPanel = vaultPanel;
		initController();
	}
	
	private void initController() {

		 vaultPanel.addAggiungiMetodoListener(e -> {
	            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(vaultPanel);
	            AddPaymentMethodDialog addDialog = new AddPaymentMethodDialog(frame);
	            addDialog.addConfirmListener(ev -> {
	                // Leggi il tipo selezionato dal dialogo
	                String type = addDialog.getSelectedType();
	                // In base al tipo, leggi i campi corretti
	                boolean success = false;
	                if(type.equals("CreditCard")) {
	                    String cardNumber = addDialog.getCcNumber();
	                    if(cardNumber == null || cardNumber.isEmpty()){
	                        JOptionPane.showMessageDialog(frame, "Inserisci un numero di carta valido.");
	                        return;
	                    }
	                    String month = addDialog.getCcMonth();
	                    String year = addDialog.getCcYear();
	                    String cvv = addDialog.getCcCVV();
	                    
	                    // Usa la factory per creare l'istanza
	                    success = ConcreteVaultFacade.getInstance().addPaymentMethod(vault,
	                                new CreditCard(cardNumber, Integer.parseInt(month), Integer.parseInt(year), Integer.parseInt(cvv), vault.getId()),
	                                type);    
	                    
	                } else if(type.equals("PayPal")) {
	                    String cardNumber = addDialog.getPpNumber();
	                    success = ConcreteVaultFacade.getInstance().addPaymentMethod(vault,
	                                new PayPal(cardNumber, vault.getId()),
	                                type);
	                } else if(type.equals("CurrentAccount")) {
	                    String iban = addDialog.getIban();
	                    success = ConcreteVaultFacade.getInstance().addPaymentMethod(vault,
	                                new CurrentAccount(iban, vault.getId()),
	                                type);
	                }
	                if(success == true) {
	                	// AGGIUNTA: Aggiorna la lista dei metodi di pagamento nel VaultPanel
	                    List<PaymentMethod> methods = LaVaultFacade.getInstance().getVaultFacade().getAllPaymentMethods(vault);
	                    DefaultListModel<String> model = new DefaultListModel<>();
	                    for (PaymentMethod pm : methods) {
	                        model.addElement(pm.toString()); 
	                    }
	                    vaultPanel.updatePaymentMethodsList(model);
	                    JOptionPane.showMessageDialog(frame, "Metodo aggiunto correttamente.");
	                } else {
	                    JOptionPane.showMessageDialog(frame, "Errore nell'aggiunta del metodo di pagamento.");
	                }
	                addDialog.dispose();
	            });
	            addDialog.addCancelListener(ev -> addDialog.dispose());
	            addDialog.setVisible(true);
	        });
		 
		// Listener per il pulsante "Deposita Denaro"
	        vaultPanel.addDepositListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Recupera il frame principale
	                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(vaultPanel);
	                // Recupera la lista dei metodi di pagamento per il vault (da facade)
	                List<PaymentMethod> methods = LaVaultFacade.getInstance().getVaultFacade().getAllPaymentMethods(vault);
	                if(methods == null || methods.isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Non sono presenti metodi di pagamento.");
	                    return;
	                }
	                // Crea il dialogo per la transazione in modalità deposit
	                PaymentMethodTransactionDialog dialog = new PaymentMethodTransactionDialog(frame, "deposit", methods);
	                dialog.addConfirmListener(ev -> {
	                    PaymentMethod selectedMethod = dialog.getSelectedPaymentMethod();
	                    double amount = dialog.getAmount();
	                    if(selectedMethod == null || amount <= 0) {
	                        JOptionPane.showMessageDialog(frame, "Seleziona un metodo e inserisci un importo valido.");
	                        return;
	                    }
	                    // Chiama il metodo della facade per depositare denaro tramite il metodo selezionato
	                    boolean success = LaVaultFacade.getInstance().getVaultFacade().depositMoneyFromPaymentMethod(vault, selectedMethod, amount);
	                    if(success) {
	                        JOptionPane.showMessageDialog(frame, "Denaro depositato correttamente.");
	                        vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getBalanceByID(vault));
	                    } else {
	                        JOptionPane.showMessageDialog(frame, "Errore nel deposito.");
	                    }
	                    dialog.dispose();
	                });
	                dialog.addCancelListener(ev -> dialog.dispose());
	                dialog.setVisible(true);
	            }
	        });
	        
	        // Listener per il pulsante "Preleva Denaro"
	        vaultPanel.addWithdrawListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(vaultPanel);
	                List<PaymentMethod> methods = LaVaultFacade.getInstance().getVaultFacade().getAllPaymentMethods(vault);
	                if(methods == null || methods.isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Non sono presenti metodi di pagamento.");
	                    return;
	                }
	                PaymentMethodTransactionDialog dialog = new PaymentMethodTransactionDialog(frame, "withdraw", methods);
	                dialog.addConfirmListener(ev -> {
	                    PaymentMethod selectedMethod = dialog.getSelectedPaymentMethod();
	                    double amount = dialog.getAmount();
	                    if(selectedMethod == null || amount <= 0) {
	                        JOptionPane.showMessageDialog(frame, "Seleziona un metodo e inserisci un importo valido.");
	                        return;
	                    }
	                    // Chiama il metodo della facade per prelevare denaro tramite il metodo selezionato
	                    boolean success = LaVaultFacade.getInstance().getVaultFacade().withdrawMoneyFromPaymentMethod(vault, selectedMethod, amount);
	                    if(success) {
	                        JOptionPane.showMessageDialog(frame, "Denaro prelevato correttamente.");
	                        vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getBalanceByID(vault));
	                    } else {
	                        JOptionPane.showMessageDialog(frame, "Errore nel prelievo.");
	                    }
	                    dialog.dispose();
	                });
	                dialog.addCancelListener(ev -> dialog.dispose());
	                dialog.setVisible(true);
	            }
	        });
	        
	        vaultPanel.addRemoveMethodListener( e -> {
	            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(vaultPanel);
	        	//recuper la lista aggiornata dei metodi di pagamento
	        	List<PaymentMethod> methods = ConcreteVaultFacade.getInstance().getAllPaymentMethods(vault);
	        	//dialog cancellazione
	        	DeletePaymentMethodDialog deletedialog = new DeletePaymentMethodDialog(frame, vault, methods);
	        	//listener per pulsante
	        	deletedialog.addDeleteListener(ev ->{
	        		PaymentMethod selectedMethod = deletedialog.getSelectedPaymentMethod();
	        		if(selectedMethod == null) {
	        			JOptionPane.showMessageDialog(deletedialog, "Seleziona un metodo di pagamento da rimuovere");
	        			return;
	        		}
	        		
	        		int confirm = JOptionPane.showConfirmDialog(deletedialog, "Sei sicuro di voler rimuovere il "
	        				+ "metodo di pagamento selezionato?", "Conferma cancellazione", JOptionPane.YES_NO_OPTION);
	        		if(confirm == JOptionPane.YES_OPTION) {
	        			boolean success = ConcreteVaultFacade.getInstance()
	        					.deletePaymentMethod(vault, selectedMethod, selectedMethod.getMethodName());
	        			if(success) {
	        				JOptionPane.showMessageDialog(deletedialog, "Metodo di pagamento rimosso con successo");
	        				//aggiorno la lista
	        				deletedialog.removeSelectedPaymentMethod();
	        				//aggiorno la lista della view
	        				List<PaymentMethod> newMethods = ConcreteVaultFacade.getInstance().getAllPaymentMethods(vault);
	        				DefaultListModel<String> newModel = new DefaultListModel<>();
	        				for(PaymentMethod pm : newMethods){
	        					newModel.addElement(pm.toString());
	        				}
	        				vaultPanel.updatePaymentMethodsList(newModel);
	        			} else {
	        				JOptionPane.showMessageDialog(deletedialog, "Errore nella cancellazione del metodo");
	        			}
	        		}
	        		
	        	});
	        	
	        	deletedialog.addCancelListener(ev -> deletedialog.dispose());
	        	deletedialog.setVisible(true);
	        });
	        
	        vaultPanel.addExecutePaymentListner(e -> {
	        	
	        	JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(vaultPanel);
	        	PaymentExecutionDialog paymentDialog = new PaymentExecutionDialog(frame);
	        	
	        	paymentDialog.addConfirmListener(ev -> {
	        		double amount = paymentDialog.getAmount();
	        		if(amount <= 0) {
	        			JOptionPane.showMessageDialog(paymentDialog, "Inserisce un importo valido");
	        			return;
	        		}
	        		String causale = paymentDialog.getCausale();
	        		
	        		boolean success = ConcreteVaultFacade.getInstance().executePayment(vault, amount, causale);
	        		if(success) {
	        			JOptionPane.showMessageDialog(paymentDialog, "Pagamento eseguito con successo");
	        			vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getBalanceByID(vault));
	        		}else {
	        			JOptionPane.showMessageDialog(paymentDialog, "Errore nell'esecuzione del pagamento");
	        		}
	        		paymentDialog.dispose();
	        	});
	        	paymentDialog.addCancelListener(ev -> paymentDialog.dispose());
	        	paymentDialog.setVisible(true);
	        });
	}
	
	public static void load() {
		User currentUser = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
		
		if (currentUser != null) {
			System.out.println("Utente loggato: " + currentUser.getId() + " - " + currentUser.getEmail());
		} else {
			System.out.println("Nessun utente loggato.");
		}
		
		try {
			vault = LaVaultFacade.getInstance().getVaultFacade().getVaultByUser(currentUser);
			
			if (vault == null) {
				System.out.println("Vault non trovato per l'utente " + currentUser.getId());
				return;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getBalanceByID(vault));
		
		//ConcreteVaultFacade.getInstance().newVaultinVirtualVault(vault, currentUser);

		ConcreteVaultFacade.getInstance().newVaultinVault(vault);

		ConcreteVaultFacade.getInstance().getVaultId(vault);
	}
	
	

}
