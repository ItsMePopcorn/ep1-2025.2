package Persistencia;

import Entities.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsultaPersistencia {

    private static final String SEPARADOR = ";";
    private static final String ARQUIVO_CONSULTAS = "consultas.csv";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public void salvar(List<Consulta> consultas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CONSULTAS))) {
            for (Consulta c : consultas) {
            
            String diagnosticoParaSalvar;
            if (c.getDiagnostico() == null) {
                diagnosticoParaSalvar = "null";
            } else {
                diagnosticoParaSalvar = c.getDiagnostico();
            }

            String medicamentosParaSalvar;
            if (c.getMedicamentos() == null) {
                medicamentosParaSalvar = "null";
            } else {
                medicamentosParaSalvar = c.getMedicamentos();
            }

            String linha = c.getPaciente().getCpf() + SEPARADOR +
                           c.getMedico().getCrm() + SEPARADOR +
                           c.getDataHora().format(DATE_TIME_FORMATTER) + SEPARADOR +
                           c.getSala() + SEPARADOR +
                           c.getStatus().name() + SEPARADOR +
                           diagnosticoParaSalvar + SEPARADOR +
                           medicamentosParaSalvar;

            writer.write(linha + "\n");
            }
        } catch (IOException e) {
                    System.err.println("Erro ao salvar consultas: " + e.getMessage());
        }
    }

    public List<Consulta> carregar(List<Paciente> pacientes, List<Medico> medicos) {
        List<Consulta> consultas = new ArrayList<>();
        File arquivo = new File(ARQUIVO_CONSULTAS);

        if (!arquivo.exists()) return consultas;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(SEPARADOR);
                if (dados.length < 7) continue;

                Paciente paciente = buscarPacientePorCpf(pacientes, dados[0]);
                Medico medico = buscarMedicoPorCrm(medicos, dados[1]);

                if (paciente != null && medico != null) {
                    LocalDateTime dataHora = LocalDateTime.parse(dados[2]);
                    Consulta consulta = new Consulta(paciente, medico, dataHora, dados[3]);
                    consulta.setStatus(StatusConsulta.valueOf(dados[4]));

                    if (!"null".equals(dados[5])) consulta.setDiagnostico(dados[5]);
                    if (!"null".equals(dados[6])) consulta.setMedicamentos(dados[6]);

                    consultas.add(consulta);

                    paciente.adicionarConsulta(consulta);
                    medico.adicionarConsultaAgenda(consulta);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar consultas: " + e.getMessage());
        }

        return consultas;
    }

    private Paciente buscarPacientePorCpf(List<Paciente> lista, String cpf) {
        for (Paciente p : lista) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    private Medico buscarMedicoPorCrm(List<Medico> lista, String crm) {
        for (Medico m : lista) {
            if (m.getCrm().equalsIgnoreCase(crm)) {
                return m;
            }
        }
        return null;
    }
}
