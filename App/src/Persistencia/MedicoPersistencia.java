package Persistencia;

import Entities.Especialidade;
import Entities.Medico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoPersistencia {

    private static final String SEPARADOR = ";";
    private static final String ARQUIVO_MEDICOS = "data/medicos.csv";

    public void salvar(List<Medico> medicos) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_MEDICOS));
            for (Medico m : medicos) {
                String linha =
                        m.getCrm() + SEPARADOR +
                        m.getNome() + SEPARADOR +
                        m.getCustoConsulta() + SEPARADOR +
                        m.getEspecialidade().getNome();
                writer.write(linha);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar médicos: " + e.getMessage());
        }
    }

    public List<Medico> carregar(List<Especialidade> especialidades) {
        List<Medico> medicos = new ArrayList<>();
        File arquivo = new File(ARQUIVO_MEDICOS);

        if (!arquivo.exists()) return medicos;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(SEPARADOR);
                if (dados.length < 4) continue;

                Especialidade especialidade = buscarEspecialidadePorNome(especialidades, dados[3]);
                if (especialidade != null) {
                    Medico medico = new Medico(dados[1], dados[0], especialidade, Double.parseDouble(dados[2]));
                    medicos.add(medico);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar médicos: " + e.getMessage());
        }

        return medicos;
    }

    private Especialidade buscarEspecialidadePorNome(List<Especialidade> lista, String nome) {
        for (Especialidade e : lista) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        return null;
    }
}
