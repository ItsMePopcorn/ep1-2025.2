package Entities;

import java.util.ArrayList;
import java.util.List;

public class Medico {
    
    private String nome;
    private String crm; 
    private Especialidade especialidade;
    private double custoConsulta; 
    private List<Consulta> agenda;

    public Medico(String nome, String crm, Especialidade especialidade, double custoConsulta) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoConsulta = custoConsulta;
        this.agenda = new ArrayList<>();
    }

    public void adicionarConsultaAgenda(Consulta consulta) {
        this.agenda.add(consulta);
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public double getCustoConsulta() {
        return custoConsulta;
    }
    
    public void setNome(String nome) {
        this.nome = nome; 
        }

    public String getNome() {
         return nome; 
    }

    public void setEspecialidade(Especialidade especialidade) {
         this.especialidade = especialidade;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public List<Consulta> getAgenda() {
        return agenda;
    }

    public void infoMedico() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("CRM: " + this.getCrm());
        System.out.println("Especialidade: " + this.getEspecialidade().getNome());
    }
}