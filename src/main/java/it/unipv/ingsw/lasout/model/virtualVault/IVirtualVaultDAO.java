package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.Vault;

import java.util.List;

public interface IVirtualVaultDAO extends IDao<VirtualVault> {



    List<VirtualVault> getAll(User user) throws Exception;

    double getBalanceFromVault(VirtualVault virtualVault) throws Exception;


    void setNewBalanceVault(VirtualVault virtualVault) throws Exception;

    void getIdFromVvP(VirtualVault virtualVault, double b, DBQuery queryGet) throws Exception;

    void getIdFromVvPPLUS(VirtualVault virtualVault, double b, DBQuery queryGet) throws Exception;
}
