package Menus;

import Services.HospitalService;

import java.util.Scanner;

public class MenuCadastros{
    private final HospitalService hospitalService;
    private final Scanner scanner;

    public MenuCadastros(HospitalService hospitalService, Scanner scanner) {
        this.hospitalService = hospitalService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Cadastros Básicos ---");
            System.out.println("1. Cadastrar Novo Plano de Saúde");
            System.out.println("2. Cadastrar Nova Especialidade");
            System.out.println("3. Cadastrar Novo Quarto");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: cadastrarNovoPlanoDeSaude(); break;
                    case 2: cadastrarNovaEspecialidade(); break;
                    case 3: cadastrarNovoQuarto(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida.");
            }
        }
    }

    private void cadastrarNovoPlanoDeSaude() {
        System.out.println("\n--- Cadastro de Plano de Saúde ---");
        System.out.print("Nome do plano: ");
        String nome = scanner.nextLine();
        System.out.print("Oferece internação curta gratuita? (S/N): ");
        String resposta = scanner.nextLine();
        boolean isGratuita = resposta.equalsIgnoreCase("S");
        hospitalService.cadastrarPlanoSaude(nome, isGratuita);
    }

    private void cadastrarNovaEspecialidade() {
        System.out.println("\n--- Cadastro de Especialidade ---");
        System.out.print("Nome da especialidade: ");
        String nome = scanner.nextLine();
        hospitalService.cadastrarEspecialidade(nome);
    }

    private void cadastrarNovoQuarto() {
        System.out.println("\n--- Cadastro de Quarto ---");
        try {
            System.out.print("Número do quarto: ");
            int numero = Integer.parseInt(scanner.nextLine());
            hospitalService.cadastrarQuarto(numero);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Número do quarto deve ser um valor numérico.");
        }
    }
}