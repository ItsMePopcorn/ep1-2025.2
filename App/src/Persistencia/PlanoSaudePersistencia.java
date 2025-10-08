package Persistencia;

import Entities.PlanoSaude;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoSaudePersistencia {

    private static final String SEPARADOR = ";";
    private static final String ARQUIVO_PLANOS = "data/planos_saude.csv";

    public void salvar(List<PlanoSaude> planos) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PLANOS));
            for (PlanoSaude p : planos) {
                writer.write(p.getNome() + SEPARADOR + p.isInternacaoGratuita());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar planos de saúde: " + e.getMessage());
        }
    }

    public List<PlanoSaude> carregar() {
        List<PlanoSaude> planos = new ArrayList<>();
        File arquivo = new File(ARQUIVO_PLANOS);

        if (!arquivo.exists()) return planos;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(SEPARADOR);
                if (dados.length >= 2) {
                    String nome = dados[0];
                    boolean internacao = Boolean.parseBoolean(dados[1]);
                    planos.add(new PlanoSaude(nome, internacao));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar planos de saúde: " + e.getMessage());
        }

        return planos;
    }
}
