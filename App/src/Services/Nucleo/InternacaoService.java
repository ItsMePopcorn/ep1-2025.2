package Services.Nucleo;

import Entities.*;
import Persistencia.InternacaoPersistencia;
import Persistencia.QuartoPersistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public class InternacaoService {
    private List<Internacao> internacoes;
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Quarto> quartos;

    public InternacaoService(List<Internacao> internacoes, List<Paciente> pacientes, List<Medico> medicos, List<Quarto> quartos, InternacaoPersistencia internacaoPersistencia, QuartoPersistencia quartoPersistencia) {
        this.internacoes = internacoes;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.quartos = quartos;
    }

    public List<Internacao> getInternacoes() {
    return new ArrayList<>(this.internacoes);
    }   

    public boolean internarPaciente(String pacienteCpf, String medicoCrm, double custoDiaria) {
        Optional<Paciente> pacienteOpt = buscarPacientePorCpf(pacienteCpf);
        Optional<Medico> medicoOpt = buscarMedicoPorCrm(medicoCrm);
        if (!pacienteOpt.isPresent() || !medicoOpt.isPresent()) {
            System.out.println("Erro: Paciente ou Médico responsável não encontrado.");
            return false;
        }
        
        boolean jaInternado = false;
        for(Internacao i : this.internacoes) {
            if (i.getPaciente().equals(pacienteOpt.get()) && i.getDataSaida() == null) {
                jaInternado = true;
                break;
            }
        }
        if (jaInternado) {
            System.out.println("Erro: Este paciente já consta como internado.");
            return false;
        }

        Optional<Quarto> quartoOpt = buscarQuartoDisponivel();
        if (!quartoOpt.isPresent()) {
            System.out.println("Erro: Não há quartos disponíveis para internação.");
            return false;
        }

        Paciente paciente = pacienteOpt.get();
        Medico medico = medicoOpt.get();
        Quarto quarto = quartoOpt.get();
        
        Internacao novaInternacao = new Internacao(paciente, medico, quarto, custoDiaria);
        quarto.setOcupado(true);
        this.internacoes.add(novaInternacao);
        paciente.adicionarInternacao(novaInternacao);
        
        System.out.println("Paciente internado com sucesso no quarto " + quarto.getNumero());
        return true;
    }

    public void darAltaPaciente(Internacao internacao) {
        if (internacao.getDataSaida() != null) {
            System.out.println("ERRO: Este paciente já recebeu alta anteriormente.");
            return;
        }
        internacao.setDataSaida(LocalDate.now());
        System.out.println("Alta registrada para o paciente.");
    }

    private Optional<Paciente> buscarPacientePorCpf(String cpf) {
        for (Paciente p : this.pacientes) { 
            if (p.getCpf().equals(cpf)){ 
                return Optional.of(p);
            }    
        }
        return Optional.empty();
    }
    
    private Optional<Medico> buscarMedicoPorCrm(String crm) {
        for (Medico m : this.medicos) { 
            if (m.getCrm().equalsIgnoreCase(crm)){
            return Optional.of(m);
            } 
        }
        return Optional.empty();
    }

    private Optional<Quarto> buscarQuartoDisponivel() {
        for (Quarto q : this.quartos) { 
            if (!q.isOcupado()){ 
                return Optional.of(q); 
            }
        }
        return Optional.empty();
    }
}