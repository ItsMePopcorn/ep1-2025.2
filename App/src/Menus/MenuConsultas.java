package Menus;

import Entities.*;
import Services.HospitalService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsultas {
    private final HospitalService hospitalService;
    private final Scanner scanner;

    public MenuConsultas(HospitalService hospitalService, Scanner scanner) {
        this.hospitalService = hospitalService;
        this.scanner = scanner;
    }

    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Consultas e Agendamentos ---");
            System.out.println("1. Agendar Nova Consulta");
            System.out.println("2. Concluir Consulta");
            System.out.println("3. Cancelar Consulta");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: agendarNovaConsulta(); 
                        break;
                    case 2: concluirConsulta(); 
                        break;
                    case 3: cancelarConsulta(); 
                        break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida.");
            }
        }
    }

    private void agendarNovaConsulta() {
        System.out.println("\n--- Agendar Nova Consulta ---");
        try {
            System.out.print("Digite o CPF do Paciente: ");
            String cpf = scanner.nextLine();
            if (!hospitalService.buscarPacientePorCpf(cpf).isPresent()) {
                System.out.println("Erro: Paciente não encontrado.");
                return;
            }

            System.out.print("Digite o CRM do Médico: ");
            String crm = scanner.nextLine();
            if (!hospitalService.buscarMedicoPorCrm(crm).isPresent()) {
                System.out.println("Erro: Médico não encontrado.");
                return;
            }

            LocalDateTime dataHora = lerDataHora();
            if (dataHora == null) return;

            System.out.print("Digite a sala da consulta: ");
            String sala = scanner.nextLine();
            
            hospitalService.agendarConsulta(cpf, crm, dataHora, sala);

        } catch (Exception e) {
            System.out.println("Ocorreu um Erro inesperado: " + e.getMessage());
        }
    }

    private void concluirConsulta() {
        System.out.println("\n--- Concluir Consulta ---");
        Consulta consulta = selecionarConsulta("Marcada");
        if (consulta == null) {
            return;
        }

        System.out.print("Digite o diagnóstico: ");
        String diagnostico = scanner.nextLine();
        System.out.print("Digite os medicamentos prescritos: ");
        String medicamentos = scanner.nextLine();
        hospitalService.concluirConsulta(consulta, diagnostico, medicamentos);

    }

    private void cancelarConsulta() {
        System.out.println("\n--- Cancelar Consulta ---");
        Consulta consulta = selecionarConsulta("Marcada");
        if (consulta == null) {
            return;
        }
        hospitalService.cancelarConsulta(consulta);
    }
    
    private LocalDateTime lerDataHora() {
        try {
            System.out.println("--- Informe a data e hora da consulta ---");
            System.out.print("Ano (AAAA) [" + LocalDate.now().getYear() + "]: ");
            String anoStr = scanner.nextLine();
            int ano;
            if (anoStr.isEmpty()) {
                ano = LocalDate.now().getYear();
            } else {
                ano = Integer.parseInt(anoStr);
            }
            System.out.print("Mês (1-12): ");
            int mes = Integer.parseInt(scanner.nextLine());
            System.out.print("Dia: ");
            int dia = Integer.parseInt(scanner.nextLine());
            System.out.print("Hora (0-23): ");
            int hora = Integer.parseInt(scanner.nextLine());
            System.out.print("Minuto (0-59): ");
            int minuto = Integer.parseInt(scanner.nextLine());
            return LocalDateTime.of(ano, mes, dia, hora, minuto);
        } catch (Exception e) {
            System.out.println("Erro: Data ou hora inválida. " + e.getMessage());
            return null;
        }
    }
    
    private Consulta selecionarConsulta(String statusFiltro) {
        System.out.print("Digite o CPF do paciente para listar as consultas: ");
        String cpf = scanner.nextLine();

        List<Consulta> consultasDoPaciente = new ArrayList<>();
        for(Consulta c : hospitalService.getConsultas()) {
            if(c.getPaciente().getCpf().equals(cpf) && c.getStatus().name().equalsIgnoreCase(statusFiltro)) {
                consultasDoPaciente.add(c);
            }
        }
        
        if (consultasDoPaciente.isEmpty()) {
            System.out.println("Nenhuma consulta com status '" + statusFiltro + "' encontrada para este paciente.");
            return null;
        }

        System.out.println("Selecione a consulta:");
        for (int i = 0; i < consultasDoPaciente.size(); i++) {
            Consulta c = consultasDoPaciente.get(i);
            System.out.println((i + 1) + " - " + c.getDataHora() + " com Dr(a). " + c.getMedico().getNome());
        }

        try {
            System.out.print("Número da consulta: ");
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= consultasDoPaciente.size()) {
                return consultasDoPaciente.get(escolha - 1);
            }
        } catch (NumberFormatException e) {
            return null;
        }

        System.out.println("Erro: Escolha inválida.");
        return null;
    }
}