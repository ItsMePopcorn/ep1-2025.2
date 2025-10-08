package Menus;

import Entities.Especialidade;
import Entities.Medico;
import Services.HospitalService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuMedicos {
    private final HospitalService hospitalService;
    private final Scanner scanner;

    public MenuMedicos(HospitalService hospitalService, Scanner scanner) {
        this.hospitalService = hospitalService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Médicos ---");
            System.out.println("1. Cadastrar Novo Médico");
            System.out.println("2. Buscar Médico por CRM");
            System.out.println("3. Listar Todos os Médicos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: cadastrarNovoMedico(); break;
                    case 2: buscarMedicoPorCRM(); break;
                    case 3: listarMedicos(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida.");
            }
        }
    }

    private void cadastrarNovoMedico() {
        System.out.println("\n--- Cadastro de Médico ---");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CRM: ");
            String crm = scanner.nextLine();
            
            Especialidade especialidadeEscolhida = selecionarEspecialidade();
            if (especialidadeEscolhida == null) return;

            System.out.print("Custo da consulta: ");
            double custo = Double.parseDouble(scanner.nextLine());

            hospitalService.cadastrarMedico(nome, crm, especialidadeEscolhida, custo);

        } catch (NumberFormatException e) {
            System.out.println("Erro: Custo inválido. Por favor, digite um número.");
        }
    }
    
    private Especialidade selecionarEspecialidade() {
        List<Especialidade> especialidades = hospitalService.getEspecialidades();
        if (especialidades.isEmpty()) {
            System.out.println("Erro: Nenhuma especialidade cadastrada. Cadastre uma no menu 'Cadastros Básicos'.");
            return null;
        }
        System.out.println("Escolha a Especialidade:");
        for (int i = 0; i < especialidades.size(); i++) {
            System.out.println((i + 1) + " - " + especialidades.get(i).getNome());
        }
        System.out.print("Número da especialidade: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        
        if (escolha < 1 || escolha > especialidades.size()) {
            System.out.println("Erro: Escolha inválida.");
            return null;
        }
        return especialidades.get(escolha - 1);
    }

    private void buscarMedicoPorCRM() {
        System.out.print("\nDigite o CRM do médico: ");
        String crm = scanner.nextLine();
        Optional<Medico> medicoOpt = hospitalService.buscarMedicoPorCrm(crm);
        
        if (medicoOpt.isPresent()) {
            medicoOpt.get().infoMedico();
        } else {
            System.out.println("Médico com CRM " + crm + " não encontrado.");
        }
    }

    private void listarMedicos() {
        System.out.println("\n--- Lista de Médicos ---");
        List<Medico> medicos = hospitalService.getMedicos();
        if (medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
            return;
        }
        for (Medico m : medicos) {
            m.infoMedico();
            System.out.println("--------------------");
        }
    }
}