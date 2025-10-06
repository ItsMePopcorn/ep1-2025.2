package Menus;

import Services.HospitalService;
import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner scanner;
    private final MenuPacientes menuPacientes;
    private final MenuMedicos menuMedicos;
    private final MenuCadastros menuCadastros;

    public MenuPrincipal(HospitalService hospitalService) {
        this.scanner = new Scanner(System.in);
        this.menuPacientes = new MenuPacientes(hospitalService, scanner);
        this.menuMedicos = new MenuMedicos(hospitalService, scanner);
        this.menuCadastros = new MenuCadastros(hospitalService, scanner);

    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1. Gerenciar Pacientes");
            System.out.println("2. Gerenciar Médicos");
            System.out.println("3. Cadastros Básicos (Planos, Quartos, Especialidades)");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: menuPacientes.exibir(); break;
                    case 2: menuMedicos.exibir(); break;
                    case 3: menuCadastros.exibir(); break;
                    case 0: System.out.println("Saindo do sistema... Até logo!"); break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida.");
            }
        }
        scanner.close();
    }
}