package Services;

import Services.Nucleo.MedicoService;
import Services.Nucleo.PacienteService;
import Services.Relatorios.RelatorioMedicos;
import Services.Relatorios.RelatorioPacientes;

public class RelatorioService {

    private final RelatorioPacientes relatorioPacientes;
    private final RelatorioMedicos relatorioMedicos;

    public RelatorioService(HospitalService hospitalService) {
        
        PacienteService pacienteService = hospitalService.getPacienteService();
        MedicoService medicoService = hospitalService.getMedicoService();

        this.relatorioPacientes = new RelatorioPacientes(pacienteService);
        this.relatorioMedicos = new RelatorioMedicos(medicoService);
    }

    public void imprimirRelatorioPacientes() {
        relatorioPacientes.imprimir();
    }

    public void imprimirRelatorioMedicos() {
        relatorioMedicos.imprimir();
    }
    
}