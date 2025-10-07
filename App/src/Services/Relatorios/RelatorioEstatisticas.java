package Services.Relatorios;

import Services.Nucleo.ConsultaService;
import Services.Nucleo.MedicoService;
import Entities.Consulta;
import Entities.Especialidade; 
import Entities.Medico;

import java.util.ArrayList; 
import java.util.List;

public class RelatorioEstatisticas {
    private final MedicoService medicoService;
    private final ConsultaService consultaService;

    public RelatorioEstatisticas(MedicoService medicoService, ConsultaService consultaService) {
        this.medicoService = medicoService;
        this.consultaService = consultaService;
    }

    public void imprimir() {
        System.out.println("\n======= ESTATÍSTICAS GERAIS DO HOSPITAL =======");
        
        List<Medico> medicos = medicoService.getMedicos();
        List<Consulta> consultas = consultaService.getConsultas();

        if (medicos.isEmpty() || consultas.isEmpty()) {
            System.out.println("Não há dados suficientes para gerar estatísticas.");
            return;
        }
        
        Medico medicoMaisAtivo = null;
        int maxConsultas = -1;

        for (Medico medico : medicos) {
            if (medico.getAgenda().size() > maxConsultas) {
                maxConsultas = medico.getAgenda().size();
                medicoMaisAtivo = medico;
            }
        }

        if (medicoMaisAtivo != null) {
            System.out.println("Médico mais ativo: Dr(a). " + medicoMaisAtivo.getNome() + " com " + maxConsultas + " consultas (totais).");
        }

        List<Especialidade> especialidadesUnicas = new ArrayList<>();
        for (Consulta c : consultas) {
            Especialidade esp = c.getMedico().getEspecialidade();
            if (!especialidadesUnicas.contains(esp)) {
                especialidadesUnicas.add(esp);
            }
        }

        String espMaisProcurada = null;
        int maxContagem = -1;
        for (Especialidade espUnica : especialidadesUnicas) {
            int contagemAtual = 0;
            
            for (Consulta c : consultas) {
                if (c.getMedico().getEspecialidade().equals(espUnica)) {
                    contagemAtual++;
                }
            }

            if (contagemAtual > maxContagem) {
                maxContagem = contagemAtual;
                espMaisProcurada = espUnica.getNome();
            }
        }
        
        if (espMaisProcurada != null) {
            System.out.println("Especialidade mais procurada: " + espMaisProcurada + " (" + maxContagem + " consultas).");
        }
        System.out.println("===============================================");
    }
}