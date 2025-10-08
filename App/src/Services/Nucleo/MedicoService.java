package Services.Nucleo;

import Entities.Especialidade;
import Entities.Medico;
import Persistencia.MedicoPersistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicoService {
    private List<Medico> medicos;

    private final MedicoPersistencia medicoPersistencia;

    public MedicoService(List<Medico> medicos, MedicoPersistencia medico) {
        this.medicos = medicos;
        this.medicoPersistencia = medico;
    }

    public List<Medico> getMedicos() {
    return new ArrayList<>(this.medicos);
    }

    public Medico cadastrarMedico(String nome, String crm, Especialidade especialidade, double custoConsulta) {
        if (buscarMedicoPorCrm(crm).isPresent()) {
            System.out.println("Erro: Médico com CRM " + crm + " já está cadastrado.");
            return null;
        }
        Medico novoMedico = new Medico(nome, crm, especialidade, custoConsulta);
        this.medicos.add(novoMedico);
        
        System.out.println("Médico cadastrado com sucesso!");

        medicoPersistencia.salvar(this.medicos);

        return novoMedico;
    }

    public Optional<Medico> buscarMedicoPorCrm(String crm) {
        for (Medico m : this.medicos) {
            if (m.getCrm().equalsIgnoreCase(crm)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }
}