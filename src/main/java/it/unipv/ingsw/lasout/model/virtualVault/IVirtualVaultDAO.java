package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.dao.IDao;
import it.unipv.ingsw.lasout.database.DBQuery;
import it.unipv.ingsw.lasout.model.vault.Vault;

public interface IVirtualVaultDAO extends IDao<VirtualVault> {


    double getBalanceFromVault(VirtualVault virtualVault) throws Exception;


    void setNewBalanceVault(VirtualVault virtualVault) throws Exception;

    void getIdFromVvP(VirtualVault virtualVault, double b, DBQuery queryGet) throws Exception;

    void getIdFromVvPPLUS(VirtualVault virtualVault, double b, DBQuery queryGet) throws Exception;
}
