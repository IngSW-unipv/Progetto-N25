package it.unipv.ingsw.lasout.model.virtualVault;

import it.unipv.ingsw.lasout.model.user.User;
import it.unipv.ingsw.lasout.model.vault.IVault;
import it.unipv.ingsw.lasout.model.vault.IVaultData;

import java.util.ArrayList;
import java.util.Scanner;


//Pojo
public class VirtualVault implements IVault{
    private int ID;
    private double balance;
    private User owner;
    private IVaultData behavior;

    //Costruttore con solo il balance
    public VirtualVault(double balance) {
        this.balance = balance;
        behavior = new VirtualVaultData();
    }
    //Costruttore con il balance e owner
    public VirtualVault(double balance, User user) {
        this.balance = balance;
        this.owner = owner;
        behavior = new VirtualVaultData();
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public User getOwner() {
        return owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public IVaultData getIVaultData() {
        return null;
    }

    public void setIVaultData(IVaultData ivaultdata) {
        behavior = ivaultdata;
    }
}





/*
    public double saldoVV;
    private int id;
    private int proprietario;
    private String nome;

    public VirtualVault(double saldoVV, int id, int proprietario, String nome) {
        this.saldoVV = saldoVV;
        this.id = id;
        this.proprietario = proprietario;
        this.nome = nome;
    }

    public VirtualVault(int id) {
    }

    public double getSaldoVV() {
        return saldoVV;
    }

    public void setSaldoVV(double saldoVV) {
        this.saldoVV = saldoVV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProprietario() {
        return proprietario;
    }

    public void setProprietario(int proprietario) {
        this.proprietario = proprietario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "VirtualVault{" +
                "saldoVV=" + saldoVV +
                ", id=" + id +
                ", proprietario=" + proprietario +
                ", nome='" + nome + '\'' +
                '}';
    }

    PROVA
    public static void main(String[] args) {

        ArrayList<String> nomiList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            //Chiedere all'utente di inserire un nome
            System.out.print("Inserisci un nome: ");
            String nome = scanner.nextLine();

            // Controllare se il nome è già presente nella lista
            if (nomiList.contains(nome)) {
                throw new Exception("Il nome \"" + nome + "\" è già presente nella lista. Reinserisci il nome.");
            }

            // Aggiungere il nome alla lista se non è presente
            nomiList.add(nome);
            System.out.println("Creato un nuovo virtual vault");

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        scanner.close();
    }*/

