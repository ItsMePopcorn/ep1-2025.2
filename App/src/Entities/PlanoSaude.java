package Entities;

import java.util.ArrayList;
import java.util.List;

public class PlanoSaude {
    private String nome;
    private List<DescontoEspecialidade> descontos;
    private boolean internacaoGratuita;

    public PlanoSaude(String nome, boolean internacaoGratuita) {
        this.nome = nome;
        this.internacaoGratuita = internacaoGratuita;
        this.descontos = new ArrayList<>();
    }

    public void adicionarDesconto(Especialidade especialidade, double percentual) {
        DescontoEspecialidade novoDesconto = new DescontoEspecialidade(especialidade, percentual);
        this.descontos.add(novoDesconto);
    }

    public double getDescontoEspecialidade(Especialidade especialidade) {
        for (DescontoEspecialidade desconto : this.descontos) {
            if (desconto.getEspecialidade().equals(especialidade)) {
                return desconto.getPercentualDesconto();
            }
        }
        return 0.0;
    }
    
    public String getNome(){ 
        return nome; 
    }
    
    public boolean isInternacaoGratuita(){ 
        return internacaoGratuita; 
    }

    public List<DescontoEspecialidade> getDescontos() {
         return descontos; 
    }
}