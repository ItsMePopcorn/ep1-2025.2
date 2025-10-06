package Menus;

import Entities.Internacao;
import Services.HospitalService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuInternacoes {
    private final HospitalService hospitalService;
    private final Scanner scanner;

    public MenuInternacoes(HospitalService hospitalService, Scanner scanner) {
        this.hospitalService = hospitalService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Internações ---");
            System.out.println("1. Registrar Nova Internação");
            System.out.println("2. Dar Alta ao Paciente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: registrarNovaInternacao(); 
                        break;
                    case 2: darAltaPaciente(); 
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

    private void registrarNovaInternacao() {
        System.out.println("\n--- Registrar Nova Internação ---");
        try {
            System.out.print("Digite o CPF do Paciente: ");
            String cpf = scanner.nextLine();
            if (!hospitalService.buscarPacientePorCpf(cpf).isPresent()) {
                System.out.println("Erro: Paciente não encontrado.");
                return;
            }

            System.out.print("Digite o CRM do Médico Responsável: ");
            String crm = scanner.nextLine();
            if (!hospitalService.buscarMedicoPorCrm(crm).isPresent()) {
                System.out.println("Erro: Médico não encontrado.");
                return;
            }

            System.out.print("Digite o custo da diária: ");
            double custoDiaria = Double.parseDouble(scanner.nextLine());

            hospitalService.internarPaciente(cpf, crm, custoDiaria);

        } catch (NumberFormatException e) {
            System.out.println("Erro: Custo da diária deve ser um número.");
        }
    }
    
    private Internacao selecionarInternacaoAtiva() {
        System.out.print("Digite o CPF do paciente para listar as internações ativas: ");
        String cpf = scanner.nextLine();

        List<Internacao> internacoesAtivas = new ArrayList<>();
        for(Internacao i : hospitalService.getInternacoes()) {
            if(i.getPaciente().getCpf().equals(cpf) && i.getDataSaida() == null) {
                internacoesAtivas.add(i);
            }
        }
        
        if (internacoesAtivas.isEmpty()) {
            System.out.println("Nenhuma internação ativa encontrada para este paciente.");
            return null;
        }

        if (internacoesAtivas.size() == 1) {
            return internacoesAtivas.get(0);
        }

        System.out.println("Selecione a internação:");
        for (int i = 0; i < internacoesAtivas.size(); i++) {
            Internacao internacao = internacoesAtivas.get(i);
            System.out.println((i + 1) + " - Entrada em " + internacao.getDataEntrada() + " no quarto " + internacao.getQuarto().getNumero());
        }

        try {
            System.out.print("Número da internação: ");
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= internacoesAtivas.size()) {
                return internacoesAtivas.get(escolha - 1);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        
        System.out.println("Erro: Escolha inválida.");
        return null;
    }

    private void darAltaPaciente() {
        System.out.println("\n--- Dar Alta ao Paciente ---");
        Internacao internacao = selecionarInternacaoAtiva();
        if (internacao == null) {
            return;
        }
        hospitalService.darAltaPaciente(internacao);
    }
}