package Entities;

import java.time.LocalDate;

public class Internacao {
    private Paciente paciente;
    private Medico medicoResponsavel;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Quarto quarto;
    private double custoDiaria;

    public Internacao(Paciente paciente, Medico medicoResponsavel, Quarto quarto, double custoDiaria) {
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = LocalDate.now();
        this.dataSaida = null;
        this.quarto = quarto;
        this.custoDiaria = custoDiaria;
    }

    public int getDuracao() {

        LocalDate dataFinal;
    
        if (this.dataSaida == null) {
            dataFinal = LocalDate.now();
        } else {
            dataFinal = this.dataSaida;
        }
        
        if (this.dataEntrada.isAfter(dataFinal)) {
            return 0;
        }

        int contadorDeDias = 0;
        LocalDate diaAtual = this.dataEntrada;
        while (diaAtual.isBefore(dataFinal)) {
            diaAtual = diaAtual.plusDays(1);
            contadorDeDias++;
        }
        
        return contadorDeDias + 1;
    }

    public double getCustoTotal(){ 
        return getDuracao()*custoDiaria; 
    }

    public Paciente getPaciente(){ 
        return paciente; 
    }

    public Medico getMedicoResponsavel(){ 
        return medicoResponsavel; 
    }

    public LocalDate getDataEntrada(){ 
        return dataEntrada; 
    }

    public void setDataEntrada(LocalDate dataEntrada){
        this.dataEntrada=dataEntrada;
    }

    public LocalDate getDataSaida(){ 
        return dataSaida; 
    }

    public void setDataSaida(LocalDate dataSaida){
        this.dataSaida=dataSaida;
        this.quarto.setOcupado(false);
    }

    public Quarto getQuarto(){ 
        return quarto; 
    }

    public double getCustoDiaria() {
    return this.custoDiaria;
    }

}