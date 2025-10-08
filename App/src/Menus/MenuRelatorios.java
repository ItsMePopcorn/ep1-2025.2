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
            System.out.println("3. Listar Consultas");
            System.out.println("4. Listar Internações");
            System.out.println("5. Ver Estatísticas");
            System.out.println("0. Voltar");
            System.out.print("Escolha um relatório: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: relatorioService.imprimirRelatorioPacientes(); 
                        break;
                    case 2: relatorioService.imprimirRelatorioMedicos(); 
                        break;
                    case 3: relatorioService.imprimirRelatorioConsultas();
                        break;
                    case 4: relatorioService.imprimirRelatorioInternados();
                        break;
                    case 5: relatorioService.imprimirEstatisticasGerais();
                        break;
                    case 0: 
                        break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida.");
            }
        }
    }
}