package Entities;

public class Paciente {

    private String nome;
    private String cpf;
    private int idade;

    public Paciente(String nome, String cpf, int idade) {
        this.nome=nome;
        this.cpf=cpf;
        this.idade=idade;
    }

    public Paciente(){
        this.nome="";
        this.cpf="";
        this.idade=0;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getNome(){
        return nome;
    }

    public void setCpf(String cpf){
        this.cpf=cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public void setIdade(int idade){
        this.idade=idade;
    }

    public int getIdade(){
        return idade;
    }

    
    
}
