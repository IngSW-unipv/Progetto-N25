package it.unipv.ingsw.lasout.virtualVault;

//e un bean
public class VirtualVault {


    private int id;
    private String nome;
    private int proprietario;
    private double saldo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getProprietario() {
        return proprietario;
    }

    public void setProprietario(int proprietario) {
        this.proprietario = proprietario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "VirtualVault{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", proprietario=" + proprietario +
                ", saldo=" + saldo +
                '}';
    }
}
