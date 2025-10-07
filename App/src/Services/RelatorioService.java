package Services;

import Services.Nucleo.ConsultaService;
import Services.Nucleo.MedicoService;
import Services.Nucleo.PacienteService;
import Services.Relatorios.RelatorioMedicos;
import Services.Relatorios.RelatorioPacientes;
import Services.Relatorios.RelatorioEstatisticas;

public class RelatorioService {

    private final RelatorioPacientes relatorioPacientes;
    private final RelatorioMedicos relatorioMedicos;
    private final RelatorioEstatisticas relatorioEstatisticas;

    public RelatorioService(HospitalService hospitalService) {
        
        PacienteService pacienteService = hospitalService.getPacienteService();
        MedicoService medicoService = hospitalService.getMedicoService();
        ConsultaService consultaService = hospitalService.getConsultaService();

        this.relatorioPacientes = new RelatorioPacientes(pacienteService);
        this.relatorioMedicos = new RelatorioMedicos(medicoService);
        this.relatorioEstatisticas = new RelatorioEstatisticas(medicoService, consultaService);
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
    
}