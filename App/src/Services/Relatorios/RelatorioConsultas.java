package Services.Relatorios;

import Entities.Consulta;
import Entities.StatusConsulta;
import Services.Nucleo.ConsultaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RelatorioConsultas {
    private final ConsultaService consultaService;

    public RelatorioConsultas(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    public void imprimir() {
        System.out.println("\n======= RELATÓRIO DE CONSULTAS =======");
        
        List<Consulta> consulta = new ArrayList<>();
        List<Consulta> todasAsConsultas = consultaService.getConsultas();
        
        for (Consulta c : todasAsConsultas) {
            if (c.getDataHora().isAfter(LocalDateTime.now()) && c.getStatus() == StatusConsulta.Marcada) {
                consulta.add(c);
            }
        }

        if (consulta.isEmpty()) {
            System.out.println("Nenhuma consulta agendada para o futuro.");
            return;
        }

        for (Consulta c : consulta) {
            System.out.println("Data: " + c.getDataHora() + " | Paciente: " + c.getPaciente().getNome() + " | Dr(a). " + c.getMedico().getNome() + " | Sala: " + c.getSala());
        }
        System.out.println("==============================================");
    }
}