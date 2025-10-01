package Entities;

public class DescontoEspecialidade {
    
    private Especialidade especialidade;
    private double percentualDesconto;

    public DescontoEspecialidade(Especialidade especialidade, double percentualDesconto){
        this.especialidade=especialidade;
        this.percentualDesconto=percentualDesconto;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public double getPercentualDesconto(){
        return percentualDesconto;
    }
}