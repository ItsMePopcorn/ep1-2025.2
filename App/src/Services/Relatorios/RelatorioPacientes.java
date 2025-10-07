package Services.Relatorios;

import Entities.Consulta;
import Entities.Internacao;
import Entities.Paciente;
import Services.Nucleo.PacienteService;
import java.util.List;

public class RelatorioPacientes {
    private final PacienteService pacienteService;

    public RelatorioPacientes(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public void imprimir() {
        System.out.println("\n======= RELATÓRIO DE PACIENTES CADASTRADOS =======");
        List<Paciente> pacientes = pacienteService.getPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado no sistema.");
            return;
        }

        for (Paciente p : pacientes) {
            p.infoPaciente();
            
            System.out.println("--- Histórico de Consultas:");
            List<Consulta> consultas = p.getHistoricoConsultas();
            if (consultas.isEmpty()) {
                System.out.println("   Nenhuma consulta registrada.");
            } else {
                for (Consulta c : consultas) {
                    System.out.println("   - Data: " + c.getDataHora().toLocalDate() + " | Dr(a). " + c.getMedico().getNome() + " (" + c.getMedico().getEspecialidade().getNome() + ")" + " | Status: " + c.getStatus());
                }
            }
            
            System.out.println("--- Histórico de Internações:");
            List<Internacao> internacoes = p.getHistoricoInternacoes();
            if (internacoes.isEmpty()) {
                System.out.println("   Nenhuma internação registrada.");
            } else {
                for (Internacao i : internacoes) {
                    String dataSaida;
                    if (i.getDataSaida() == null) {
                        dataSaida = "ATIVA";
                    } else {
                        dataSaida = i.getDataSaida().toString();
                    }
                    System.out.println("   - Entrada: " + i.getDataEntrada() + " | Saída: " + dataSaida + " | Dr(a). " + i.getMedicoResponsavel().getNome() + " | Dias: " + i.getDuracao());
                }
            }
            System.out.println("-----------------------------------------------");
        }
    }
}