package Services.Nucleo;

import Entities.*;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ConsultaService {
    private List<Consulta> consultas;
    private List<Paciente> pacientes;
    private List<Medico> medicos;

    public ConsultaService(List<Consulta> consultas, List<Paciente> pacientes, List<Medico> medicos) {
        this.consultas = consultas;
        this.pacientes = pacientes;
        this.medicos = medicos;
    }

    public boolean agendarConsulta(String pacienteCpf, String medicoCrm, LocalDateTime dataHora, String sala) {
        Optional<Paciente> pacienteOpt = buscarPacientePorCpf(pacienteCpf);
        Optional<Medico> medicoOpt = buscarMedicoPorCrm(medicoCrm);

        if (!pacienteOpt.isPresent() || !medicoOpt.isPresent()) {
            System.out.println("Erro: Paciente ou Médico não encontrado.");
            return false;
        }
        
        Medico medico = medicoOpt.get();

        boolean medicoOcupado = false;
        for (Consulta c : medico.getAgenda()) {
            if (c.getDataHora().isEqual(dataHora) && c.getStatus() == StatusConsulta.Marcada) {
                medicoOcupado = true;
                break;
            }
        }
        if (medicoOcupado) {
            System.out.println("Erro: O Dr(a). " + medico.getNome() + " já possui uma consulta neste horário.");
            return false;
        }
        
        boolean localOcupado = false;
        for (Consulta c : this.consultas) {
            if (c.getSala().equalsIgnoreCase(sala) && c.getDataHora().isEqual(dataHora) && c.getStatus() == StatusConsulta.Marcada) {
                localOcupado = true;
                break;
            }
        }
        if (localOcupado) {
            System.out.println("Erro: A sala " + sala + " já estará ocupada neste horário.");
            return false;
        }

        Paciente paciente = pacienteOpt.get();
        Consulta novaConsulta = new Consulta(paciente, medico, dataHora, sala);
        this.consultas.add(novaConsulta);
        paciente.adicionarConsulta(novaConsulta);
        medico.adicionarConsultaAgenda(novaConsulta);

        System.out.println("Consulta agendada com sucesso!");
        return true;
    }

    public void concluirConsulta(Consulta consulta, String diagnostico, String medicamentos) {
        if (consulta.getStatus() != StatusConsulta.Marcada) {
            System.out.println("ERRO: Ação não permitida. A consulta não está com o status 'Marcada'.");
            return;
        }
        consulta.setDiagnostico(diagnostico);
        consulta.setMedicamentos(medicamentos);
        consulta.setStatus(StatusConsulta.Concluida);
        System.out.println("Consulta concluída com sucesso.");
    }

    public void cancelarConsulta(Consulta consulta) {
        if (consulta.getStatus() != StatusConsulta.Marcada) {
            System.out.println("ERRO: Ação não permitida. A consulta não pode mais ser cancelada.");
            return;
        }
        consulta.setStatus(StatusConsulta.Cancelada);
        System.out.println("Consulta cancelada com sucesso.");
    }

    private Optional<Paciente> buscarPacientePorCpf(String cpf) {
        for (Paciente p : this.pacientes) { 
            if (p.getCpf().equals(cpf)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
    
    private Optional<Medico> buscarMedicoPorCrm(String crm) {
        for (Medico m : this.medicos) { 
            if (m.getCrm().equalsIgnoreCase(crm)){
            return Optional.of(m); 
            }   
        }
        return Optional.empty();
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<>(this.consultas);
    }

}