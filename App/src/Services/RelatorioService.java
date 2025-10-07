package Services;

import Services.Nucleo.ConsultaService;
import Services.Nucleo.InternacaoService;
import Services.Nucleo.MedicoService;
import Services.Nucleo.PacienteService;
import Services.Relatorios.RelatorioMedicos;
import Services.Relatorios.RelatorioPacientes;
import Services.Relatorios.RelatorioEstatisticas;
import Services.Relatorios.RelatorioConsultas;
import Services.Relatorios.RelatorioInternacoes;

public class RelatorioService {

    private final RelatorioPacientes relatorioPacientes;
    private final RelatorioMedicos relatorioMedicos;
    private final RelatorioEstatisticas relatorioEstatisticas;
    private final RelatorioConsultas relatorioConsultas;
    private final RelatorioInternacoes relatorioInternacoes;

    public RelatorioService(HospitalService hospitalService) {
        
        PacienteService pacienteService = hospitalService.getPacienteService();
        MedicoService medicoService = hospitalService.getMedicoService();
        ConsultaService consultaService = hospitalService.getConsultaService();
        InternacaoService internacaoService = hospitalService.getInternacaoService();

        this.relatorioPacientes = new RelatorioPacientes(pacienteService);
        this.relatorioMedicos = new RelatorioMedicos(medicoService);
        this.relatorioEstatisticas = new RelatorioEstatisticas(medicoService, consultaService);
        this.relatorioConsultas = new RelatorioConsultas(consultaService);
        this.relatorioInternacoes = new RelatorioInternacoes(internacaoService);
    }

    public void imprimirRelatorioPacientes() {
        relatorioPacientes.imprimir();
    }

    public void imprimirRelatorioMedicos() {
        relatorioMedicos.imprimir();
    }

    public void imprimirEstatisticasGerais() {
        relatorioEstatisticas.imprimir();
    }

    public void imprimirRelatorioInternados() {
        relatorioInternacoes.imprimir();
    }
    
    public void imprimirRelatorioConsultas(){
        relatorioConsultas.imprimir();
    }
}