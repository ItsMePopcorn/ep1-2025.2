package Services;

import Entities.*;
import Services.Nucleo.*;
import Persistencia.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class HospitalService {

    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> consultas;
    private List<Internacao> internacoes;
    private List<Especialidade> especialidades;
    private List<PlanoSaude> planosDeSaude;
    private List<Quarto> quartos;

    private final PacientePersistencia pacientePersistencia;
    private final MedicoPersistencia medicoPersistencia;
    private final ConsultaPersistencia consultaPersistencia;
    private final InternacaoPersistencia internacaoPersistencia;
    private final EspecialidadePersistencia especialidadePersistencia;
    private final PlanoSaudePersistencia planoSaudePersistencia;
    private final QuartoPersistencia quartoPersistencia;

    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final ConsultaService consultaService;
    private final InternacaoService internacaoService;
    private final CadastroService cadastroService;

    public HospitalService() {
        this.pacientePersistencia = new PacientePersistencia();
        this.medicoPersistencia = new MedicoPersistencia();
        this.consultaPersistencia = new ConsultaPersistencia();
        this.internacaoPersistencia = new InternacaoPersistencia();
        this.especialidadePersistencia = new EspecialidadePersistencia();
        this.planoSaudePersistencia = new PlanoSaudePersistencia();
        this.quartoPersistencia = new QuartoPersistencia();
        
        carregarDadosIniciais();

        this.pacienteService = new PacienteService(this.pacientes, this.pacientePersistencia);
        this.medicoService = new MedicoService(this.medicos, this.medicoPersistencia);
        this.consultaService = new ConsultaService(this.consultas, this.pacientes, this.medicos, this.consultaPersistencia);
        this.internacaoService = new InternacaoService(this.internacoes, this.pacientes, this.medicos, this.quartos, this.internacaoPersistencia, this.quartoPersistencia);
        this.cadastroService = new CadastroService(this.especialidades, this.planosDeSaude, this.quartos, this.especialidadePersistencia, this.planoSaudePersistencia, this.quartoPersistencia);
    }

    private void carregarDadosIniciais() {
        System.out.println("Carregando dados do sistema...");
        
        this.especialidades = especialidadePersistencia.carregar();
        this.planosDeSaude = planoSaudePersistencia.carregar();
        this.quartos = quartoPersistencia.carregar();
        this.medicos = medicoPersistencia.carregar(this.especialidades);
        this.pacientes = pacientePersistencia.carregar(this.planosDeSaude);
        this.consultas = consultaPersistencia.carregar(this.pacientes, this.medicos);
        this.internacoes = internacaoPersistencia.carregar(this.pacientes, this.medicos, this.quartos);
    }
      

    public Paciente cadastrarPaciente(String nome, String cpf, int idade, PlanoSaude plano) {
        return pacienteService.cadastrarPaciente(nome, cpf, idade, plano);
    }

    public Optional<Paciente> buscarPacientePorCpf(String cpf) {
        return pacienteService.buscarPacientePorCpf(cpf);
    }

    public Medico cadastrarMedico(String nome, String crm, Especialidade especialidade, double custoConsulta) {
        return medicoService.cadastrarMedico(nome, crm, especialidade, custoConsulta);
    }

    public Optional<Medico> buscarMedicoPorCrm(String crm) {
        return medicoService.buscarMedicoPorCrm(crm);
    }

    public boolean agendarConsulta(String pacienteCpf, String medicoCrm, LocalDateTime dataHora, String sala) {
        return consultaService.agendarConsulta(pacienteCpf, medicoCrm, dataHora, sala);
    }

    public boolean internarPaciente(String pacienteCpf, String medicoCrm, double custoDiaria) {
        return internacaoService.internarPaciente(pacienteCpf, medicoCrm, custoDiaria);
    }

    public Especialidade cadastrarEspecialidade(String nome) {
        return cadastroService.cadastrarEspecialidade(nome);
    }

    public void concluirConsulta(Consulta consulta, String diagnostico, String medicamentos) {
    this.consultaService.concluirConsulta(consulta, diagnostico, medicamentos);
    }

    public void cancelarConsulta(Consulta consulta) {
        this.consultaService.cancelarConsulta(consulta);
    }

    public void darAltaPaciente(Internacao internacao) {
        this.internacaoService.darAltaPaciente(internacao);
    }

    public PlanoSaude cadastrarPlanoSaude(String nome, boolean internacaoGratuita) {
        return cadastroService.cadastrarPlanoSaude(nome, internacaoGratuita);
    }
    
    public Quarto cadastrarQuarto(int numero) {
        return cadastroService.cadastrarQuarto(numero);
    }

    public List<Paciente> getPacientes() { 
        return pacientes; 
    }

    public List<Medico> getMedicos() { 
        return medicos; 
    }

    public List<Consulta> getConsultas() { 
        return consultas; 
    }

    public List<Internacao> getInternacoes() { 
        return internacoes; 
    }

    public List<Especialidade> getEspecialidades() { 
        return especialidades; 
    }

    public List<PlanoSaude> getPlanosDeSaude() {
        return planosDeSaude; 
    }

    public List<Quarto> getQuartos() { 
        return quartos; 
    }

    public PacienteService getPacienteService() { 
        return this.pacienteService; 
    }
    
    public MedicoService getMedicoService() { 
        return this.medicoService; 
    }

    public ConsultaService getConsultaService() { 
        return this.consultaService; 
    }

    public InternacaoService getInternacaoService() { 
        return this.internacaoService; 
    }

}