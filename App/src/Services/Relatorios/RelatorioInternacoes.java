package Services.Relatorios;

import Entities.Internacao;
import Services.Nucleo.InternacaoService;

import java.util.ArrayList;
import java.util.List;

public class RelatorioInternacoes {
    private final InternacaoService internacaoService;

    public RelatorioInternacoes(InternacaoService internacaoService) {
        this.internacaoService = internacaoService;
    }

    public void imprimir() {
        System.out.println("\n======= RELATÓRIO DE PACIENTES INTERNADOS ATUALMENTE =======");
        
        List<Internacao> internados = new ArrayList<>();
        for (Internacao i : internacaoService.getInternacoes()) {
            if (i.getDataSaida() == null) {
                internados.add(i);
            }
        }
            
        if (internados.isEmpty()) {
            System.out.println("Nenhum paciente internado no momento.");
            return;
        }

        for (Internacao i : internados) {
            System.out.println("Paciente: " + i.getPaciente().getNome() + " (CPF: " + i.getPaciente().getCpf() + ")");
            System.out.println("   Quarto: " + i.getQuarto().getNumero() + " | Dr(a). Responsável: " + i.getMedicoResponsavel().getNome());
            System.out.println("   Data de Entrada: " + i.getDataEntrada() + " (" + i.getDuracao() + " dias internado)");
            System.out.println("----------------------------------------------------------");
        }
    }
}