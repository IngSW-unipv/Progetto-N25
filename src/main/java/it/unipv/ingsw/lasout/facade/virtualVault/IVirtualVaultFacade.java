package it.unipv.ingsw.lasout.facade.virtualVault;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.virtualVault.VirtualVault;

public interface IVirtualVaultFacade {

    boolean newVirtualVault(VirtualVault virtualVault, User user);

    boolean getVirtualVault(VirtualVault virtualVault);

    boolean editVirtualVault(VirtualVault virtualVault);

    boolean deleteVirtualVault(VirtualVault virtualVault);

}
