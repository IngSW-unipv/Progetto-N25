package it.unipv.ingsw.lasout.controller.vault;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;
import it.unipv.ingsw.lasout.model.vault.VaultDAO;
import it.unipv.ingsw.lasout.view.vault.VaultPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VaultController {

	private static Vault vault;
	private static VaultPanel vaultPanel;

	public VaultController(VaultPanel vaultPanel) {
		this.vaultPanel = vaultPanel;
		initController();
	}

//    public VaultController(Vault vault) {
//        if (vault == null) {
//            throw new IllegalArgumentException("Il Vault non può essere null");
//        }
//        this.vault = vault;
//        initController();
//    }

	private void initController() {
		
		vaultPanel.addAggiornaListener(e->{
			JOptionPane.showMessageDialog(vaultPanel, "Inserisci un nome valido", "Errore", JOptionPane.ERROR_MESSAGE);

		});



		//    	
//    	vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getVault(new Vault(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser()).getBalance()));
//    	
//		User u = LaVaultFacade.getInstance().getSessionFacade().getLoggedUser();
//		
//		vault.setOwner(new User(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser().getId()));
//		
//    	LaVaultFacade.getInstance().getVaultFacade().getVault(vault);
	}

	public static void load(){
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

//			vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getVaultByUserID(LaVaultFacade.getInstance().getSessionFacade().getLoggedUser()).getSaldo());
		vaultPanel.updateSaldo(LaVaultFacade.getInstance().getVaultFacade().getBalanceByID(vault));
	}

}
