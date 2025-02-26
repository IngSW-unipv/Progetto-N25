package it.unipv.ingsw.lasout.facade.virtualVault;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;

import java.util.List;

public interface VirtualVaultFacade {

    boolean newVirtualVault(VirtualVault virtualVault, User user);

    double getBalanceFromVault(VirtualVault virtualVault);

    VirtualVault getVirtualVault(VirtualVault virtualVault);

    List<VirtualVault> getAllVirtualVault(User user);

    boolean editVirtualVault(VirtualVault virtualVault);

    boolean deleteVirtualVault(VirtualVault virtualVault);

}
