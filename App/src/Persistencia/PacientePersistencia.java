package Persistencia;

import Entities.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacientePersistencia {

    private static final String SEPARADOR = ";";
    private static final String ARQUIVO_PACIENTES = "pacientes.csv";

    public void salvar(List<Paciente> pacientes) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PACIENTES));
            for (Paciente p : pacientes) {
                String tipo = "COMUM";
                String nomePlano = "N/A";

                if (p instanceof PacienteEspecial) {
                    tipo = "ESPECIAL";
                    nomePlano = ((PacienteEspecial) p).getPlano().getNome();
                }

                String linha = p.getCpf() + SEPARADOR +
                               p.getNome() + SEPARADOR +
                               p.getIdade() + SEPARADOR +
                               tipo + SEPARADOR +
                               nomePlano;
                writer.write(linha);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }

    public List<Paciente> carregar(List<PlanoSaude> planos) {
        List<Paciente> pacientes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_PACIENTES);

        if (!arquivo.exists()) return pacientes;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(SEPARADOR);
                if (dados.length < 4) continue;

                String cpf = dados[0];
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                String tipo = dados[3];

                if (tipo.equals("ESPECIAL") && dados.length >= 5) {
                    PlanoSaude plano = buscarPlanoPorNome(planos, dados[4]);
                    if (plano != null) {
                        pacientes.add(new PacienteEspecial(nome, cpf, idade, plano));
                    }
                } else {
                    pacientes.add(new Paciente(nome, cpf, idade));
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar pacientes: " + e.getMessage());
        }

        return pacientes;
    }

    private PlanoSaude buscarPlanoPorNome(List<PlanoSaude> planos, String nome) {
        for (PlanoSaude p : planos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
}
