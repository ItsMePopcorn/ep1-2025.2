package Entities;

public class PacienteEspecial extends Paciente {

    private int plano;

    public PacienteEspecial(String nome, String cpf, int idade, int plano) {
        super(nome, cpf, idade);
        this.plano=plano;
    }

    public void setPlano(int plano){
        this.plano=plano;
    }

    public int getPlano(){
        return plano;
    }

}
