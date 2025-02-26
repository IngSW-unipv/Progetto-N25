package it.unipv.ingsw.lasout.view.virtualVault;

public class VirtualVaultItem {
    private int id;
    private String name;



    public VirtualVaultItem(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }




    @Override
    public String toString() {return name;}
}
