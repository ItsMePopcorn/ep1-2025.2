package Menus;

import Services.RelatorioService;
import java.util.Scanner;

public class MenuRelatorios {
    private final RelatorioService relatorioService;
    private final Scanner scanner;

    public MenuRelatorios(RelatorioService relatorioService, Scanner scanner) {
        this.relatorioService = relatorioService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Submenu de Relatórios ---");
            System.out.println("1. Listar Pacientes com Histórico");
            System.out.println("2. Listar Médicos");
            //consultas
            //internacoes
            //estatisticas
            System.out.println("0. Voltar");
            System.out.print("Escolha um relatório: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: relatorioService.imprimirRelatorioPacientes(); break;
                    case 2: relatorioService.imprimirRelatorioMedicos(); break;
                    case 3: //relatorio das consultas
                    case 4: //relatorio das internacoes
                    case 5: //relatorio das estatisticas
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida.");
            }
        }
    }
}