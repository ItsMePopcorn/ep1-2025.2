package Persistencia;

import Entities.Especialidade;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadePersistencia {

    private static final String ARQUIVO_ESPECIALIDADES = "data/especialidades.csv";

    public void salvar(List<Especialidade> especialidades) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ESPECIALIDADES));
            for (Especialidade e : especialidades) {
                writer.write(e.getNome());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar especialidades: " + e.getMessage());
        }
    }

    public List<Especialidade> carregar() {
        List<Especialidade> especialidades = new ArrayList<>();
        File arquivo = new File(ARQUIVO_ESPECIALIDADES);

        if (!arquivo.exists()) return especialidades;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    especialidades.add(new Especialidade(linha));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao carregar especialidades: " + e.getMessage());
        }

        return especialidades;
    }
}
