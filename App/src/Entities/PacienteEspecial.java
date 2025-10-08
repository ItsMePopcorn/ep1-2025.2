package Entities;

public class PacienteEspecial extends Paciente {
    private PlanoSaude plano;

    public PacienteEspecial(String nome, String cpf, int idade, PlanoSaude plano) {
        super(nome, cpf, idade);
        this.plano = plano;
    }

    @Override 
    public double custoConsulta(Consulta consulta){
        double custoPadrao = super.custoConsulta(consulta);
        Especialidade especialidade = consulta.getMedico().getEspecialidade();
        double descontoPlano = plano.getDescontoEspecialidade(especialidade);
        return custoPadrao*(1 - descontoPlano);
    }
    
    @Override
    public double custoInternacao(Internacao internacao){

        if (plano.isInternacaoGratuita() && internacao.getDuracao() <= 7){
            return 0.0;
        }

        double custoPadrao = super.custoInternacao(internacao);
        return custoPadrao * 0.7;
    }

    @Override
    public void infoPaciente() {
        super.infoPaciente();
        System.out.println("Plano: " + this.plano.getNome()); 
        System.out.println("--------------------------------------\n");
    }

    public PlanoSaude getPlano() {
         return plano; 
    }

    public void setPlano(PlanoSaude plano) {
        this.plano = plano; 
    }
}