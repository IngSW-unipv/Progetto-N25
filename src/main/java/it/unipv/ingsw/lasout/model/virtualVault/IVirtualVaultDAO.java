package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.dao.IDao;

public interface IVirtualVaultDAO extends IDao<VirtualVault> {


    double getBalanceFromVault(VirtualVault virtualVault) throws Exception;
}
