package it.unipv.ingsw.lasout.model.virtualVault;

import java.util.ArrayList;
import java.util.Scanner;

public class VirtualVault {
    public double saldoVV;
    private int id;
    private int proprietario;

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

    private String nome;


    @Override
    public String toString() {
        return "VirtualVault{" +
                "saldoVV=" + saldoVV +
                ", id=" + id +
                ", proprietario=" + proprietario +
                ", nome='" + nome + '\'' +
                '}';
    }

    //PROVA
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
    }
}

