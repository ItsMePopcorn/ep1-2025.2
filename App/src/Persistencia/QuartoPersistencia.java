package Persistencia;

import Entities.Quarto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuartoPersistencia {

    private static final String SEPARADOR = ";";
    private static final String ARQUIVO_QUARTOS = "data/quartos.csv";

    public void salvar(List<Quarto> quartos) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_QUARTOS));
            for (Quarto q : quartos) {
                writer.write(q.getNumero() + SEPARADOR + q.isOcupado());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar quartos: " + e.getMessage());
        }
    }

    public List<Quarto> carregar() {
        List<Quarto> quartos = new ArrayList<>();
        File arquivo = new File(ARQUIVO_QUARTOS);

        if (!arquivo.exists()) return quartos;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(SEPARADOR);
                if (dados.length >= 2) {
                    int numero = Integer.parseInt(dados[0]);
                    boolean ocupado = Boolean.parseBoolean(dados[1]);

                    Quarto q = new Quarto(numero);
                    q.setOcupado(ocupado);
                    quartos.add(q);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar quartos: " + e.getMessage());
        }

        return quartos;
    }
}
