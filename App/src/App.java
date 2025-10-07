import Entities.Especialidade;
import Services.HospitalService;
import Services.RelatorioService;
import Menus.MenuPrincipal;

public class App {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  Bem-vindo ao Sistema do Hospital Universitário");
        System.out.println("==============================================");

        HospitalService hospitalService = new HospitalService();
        RelatorioService relatorioService = new RelatorioService(hospitalService);

        hospitalService.cadastrarEspecialidade("Cardiologia");
        hospitalService.cadastrarEspecialidade("Pediatria");
        Especialidade cardiologia = hospitalService.getEspecialidades().get(0);
        hospitalService.cadastrarMedico("Dr. Robson", "67890", cardiologia, 350.0);
        hospitalService.cadastrarPaciente("Joãozinho", "6789", 45, null);

        MenuPrincipal menu = new MenuPrincipal(hospitalService, relatorioService);
        menu.exibir();
    }
}