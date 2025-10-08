package Persistencia;

import Entities.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InternacaoPersistencia {

    private static final String SEPARADOR = ";";
    private static final String ARQUIVO_INTERNACOES = "data/internacoes.csv";

    public void salvar(List<Internacao> internacoes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_INTERNACOES))){
            for (Internacao i : internacoes) {
            String dataSaidaParaSalvar;

            if (i.getDataSaida() == null) {
                dataSaidaParaSalvar = "null";
            } else {
                dataSaidaParaSalvar = i.getDataSaida().toString();
            }

            String linha =
                    i.getPaciente().getCpf() + SEPARADOR +
                    i.getMedicoResponsavel().getCrm() + SEPARADOR +
                    i.getQuarto().getNumero() + SEPARADOR +
                    i.getDataEntrada().toString() + SEPARADOR +
                    dataSaidaParaSalvar + SEPARADOR + 
                    i.getCustoDiaria();
            writer.write(linha);
            writer.newLine();
        }
        writer.close();
    } catch (IOException e) {
        System.out.println("Erro ao salvar internações: " + e.getMessage());
    }
}

    public List<Internacao> carregar(List<Paciente> pacientes, List<Medico> medicos, List<Quarto> quartos) {
        List<Internacao> internacoes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_INTERNACOES);

        if (!arquivo.exists()) return internacoes;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(SEPARADOR);
                if (dados.length < 6) continue;

                Paciente paciente = buscarPacientePorCpf(pacientes, dados[0]);
                Medico medico = buscarMedicoPorCrm(medicos, dados[1]);
                Quarto quarto = buscarQuartoPorNumero(quartos, Integer.parseInt(dados[2]));

                if (paciente != null && medico != null && quarto != null) {
                    LocalDate dataEntrada = LocalDate.parse(dados[3]);
                    Internacao internacao = new Internacao(paciente, medico, quarto, Double.parseDouble(dados[5]));
                    internacao.setDataEntrada(dataEntrada);

                    if (!"null".equals(dados[4])) {
                        internacao.setDataSaida(LocalDate.parse(dados[4]));
                    }

                    internacoes.add(internacao);
                    paciente.adicionarInternacao(internacao);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar internações: " + e.getMessage());
        }

        return internacoes;
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

    private Quarto buscarQuartoPorNumero(List<Quarto> lista, int numero) {
        for (Quarto q : lista) {
            if (q.getNumero() == numero) {
                return q;
            }
        }
        return null;
    }
}
