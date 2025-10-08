package Menus;

import Entities.Paciente;
import Entities.PlanoSaude;
import Services.HospitalService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuPacientes {
    private final HospitalService hospitalService;
    private final Scanner scanner;

    public MenuPacientes(HospitalService hospitalService, Scanner scanner) {
        this.hospitalService = hospitalService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Pacientes ---");
            System.out.println("1. Cadastrar Novo Paciente");
            System.out.println("2. Buscar Paciente por CPF");
            System.out.println("3. Listar Todos os Pacientes");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: cadastrarNovoPaciente(); 
                        break;
                    case 2: buscarPacientePorCPF(); 
                        break;
                    case 3: listarPacientes(); 
                        break;
                    case 0: 
                        break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
            }
        }
    }

    private void cadastrarNovoPaciente() {
        System.out.println("\n--- Cadastro de Paciente ---");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine());

            System.out.print("O paciente possui plano de saúde? (S/N): ");
            String temPlano = scanner.nextLine();

            PlanoSaude planoEscolhido = null;
            if (temPlano.equalsIgnoreCase("S")) {
                planoEscolhido = selecionarPlano();
                if (planoEscolhido == null) {
                    System.out.println("Cadastro cancelado, pois nenhum plano foi selecionado.");
                    return;
                }
            }
            hospitalService.cadastrarPaciente(nome, cpf, idade, planoEscolhido);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Idade deve ser um número.");
        }
    }

    private void buscarPacientePorCPF() {
        System.out.print("\nDigite o CPF do paciente a ser buscado: ");
        String cpf = scanner.nextLine();
        Optional<Paciente> pacienteOpt = hospitalService.buscarPacientePorCpf(cpf);
        
        if (pacienteOpt.isPresent()) {
            pacienteOpt.get().infoPaciente();
        } else {
            System.out.println("Paciente com CPF " + cpf + " não encontrado.");
        }
    }

    private void listarPacientes() {
        System.out.println("\n--- Lista de Pacientes Cadastrados ---");
        List<Paciente> pacientes = hospitalService.getPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente p : pacientes) {
            p.infoPaciente();
        }
    }
    
    private PlanoSaude selecionarPlano() {
        System.out.println("\n--- Seleção de Plano de Saúde ---");
        List<PlanoSaude> planos = hospitalService.getPlanosDeSaude(); 
        if (planos.isEmpty()) {
            System.out.println("Erro: Nenhum plano de saúde cadastrado no sistema. Cadastre um primeiro.");
            return null;
        }
        System.out.println("Escolha um dos planos abaixo:");
        for (int i = 0; i < planos.size(); i++) {
            System.out.println((i + 1) + " - " + planos.get(i).getNome());
        }
        try {
            System.out.print("Número do plano: ");
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= planos.size()) {
                return planos.get(escolha - 1);
            } else {
                System.out.println("Erro: Escolha inválida.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Entrada inválida. Digite um número.");
            return null;
        }
    }
}