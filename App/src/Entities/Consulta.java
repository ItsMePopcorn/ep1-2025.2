package Entities;

import java.time.LocalDateTime;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String sala;
    private String diagnostico;
    private String medicamentos;
    private StatusConsulta status;

    public Consulta (Paciente paciente, Medico medico, LocalDateTime dataHora, String sala){
        this.paciente=paciente;
        this.medico=medico;
        this.sala=sala;
        this.status=StatusConsulta.Marcada;
        this.dataHora = dataHora;
    }

    public Paciente getPaciente(){
        return paciente;
    }

    public Medico getMedico(){
        return medico;
    }

    public LocalDateTime getDataHora(){
        return dataHora;
    }

    public String getSala(){
        return sala;
    }

    public StatusConsulta getStatus(){
        return status;
    }

    public void setStatus(StatusConsulta status) {
    this.status = status;
    }


    public String getDiagnostico(){
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico){
        this.diagnostico=diagnostico;
    }

    public String getMedicamentos(){
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos){
        this.medicamentos=medicamentos;
    }
    
}