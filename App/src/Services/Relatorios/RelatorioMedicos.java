package Services.Relatorios;

import Entities.Consulta;
import Entities.Medico;
import Entities.StatusConsulta;
import Services.Nucleo.MedicoService;
import java.util.List;

public class RelatorioMedicos {
    private final MedicoService medicoService;

    public RelatorioMedicos(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    public void imprimir() {
        System.out.println("\n======= RELATÓRIO DE MÉDICOS CADASTRADOS =======");
        List<Medico> medicos = medicoService.getMedicos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado no sistema.");
            return;
        }

        for (Medico m : medicos) {
            int consultasConcluidas = 0;
            for (Consulta c : m.getAgenda()) {
                if (c.getStatus() == StatusConsulta.Concluida) {
                    consultasConcluidas++;
                }
            }
            
            System.out.println("Nome: Dr(a). " + m.getNome() + " | CRM: " + m.getCrm());
            String custoFormatado = String.format("%.2f", m.getCustoConsulta());
            System.out.println("   Especialidade: " + m.getEspecialidade().getNome() + " | Custo Padrão: R$" + custoFormatado);
            System.out.println("   Consultas Concluídas: " + consultasConcluidas);
            System.out.println("-----------------------------------------------");
        }
    }
}