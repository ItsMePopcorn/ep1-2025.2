package Entities;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String nome;
    private String cpf;
    private int idade;
    private List<Consulta> historicoConsultas;
    private List<Internacao> historicoInternacoes;

    public Paciente(String nome, String cpf, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.historicoConsultas = new ArrayList<>();
        this.historicoInternacoes = new ArrayList<>(); 
    }
    
    public double custoConsulta(Consulta consulta) {
        double custoPadrao = consulta.getMedico().getCustoConsulta();
        if (this.getIdade() >= 60) {
            return custoPadrao * 0.8;
        }
        return custoPadrao;
    }

    public double custoInternacao(Internacao internacao){
        double custoPadrao = internacao.getCustoTotal();
        if (this.idade >= 60){
            return custoPadrao * 0.85;
        }
        return custoPadrao;
    }

    public void adicionarConsulta(Consulta c){
        this.historicoConsultas.add(c); 
    }

    public void adicionarInternacao(Internacao i){
        this.historicoInternacoes.add(i); 
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
    
    public List<Consulta> getHistoricoConsultas(){
        return historicoConsultas; 
    }

    public List<Internacao> getHistoricoInternacoes(){ 
        return historicoInternacoes; 
    }
    
    public void infoPaciente(){
        System.out.println("------- Informações do Paciente -------");
        System.out.println("Nome: " + this.getNome());
        System.out.println("CPF: " + this.getCpf());
        System.out.println("Idade: " + this.getIdade());
        System.out.println("---------------------------------------\n");
    }
}