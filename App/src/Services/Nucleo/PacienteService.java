package Services.Nucleo;

import Entities.Paciente;
import Entities.PacienteEspecial;
import Entities.PlanoSaude;
import Persistencia.PacientePersistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteService {
    private List<Paciente> pacientes;

    private final PacientePersistencia pacientePersistencia;

    public PacienteService(List<Paciente> pacientes, PacientePersistencia paciente) {
        this.pacientes = pacientes;
        this.pacientePersistencia = paciente;
    }

    public List<Paciente> getPacientes() {
        return new ArrayList<>(this.pacientes);
    }

    public Paciente cadastrarPaciente(String nome, String cpf, int idade, PlanoSaude plano) {
        if (buscarPacientePorCpf(cpf).isPresent()) {
            System.out.println("Erro: Paciente com CPF " + cpf + " já está cadastrado.");
            return null;
        }
        
        Paciente novoPaciente;

        if (plano != null) { 
            novoPaciente = new PacienteEspecial(nome, cpf, idade, plano);
        } else {
            novoPaciente = new Paciente(nome, cpf, idade);
        }
        
        this.pacientes.add(novoPaciente);

        pacientePersistencia.salvar(this.pacientes);
        
        System.out.println("Paciente cadastrado com sucesso!");
        return novoPaciente;
    }

    public Optional<Paciente> buscarPacientePorCpf(String cpf) {
        for (Paciente p : this.pacientes) {
            if (p.getCpf().equals(cpf)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}