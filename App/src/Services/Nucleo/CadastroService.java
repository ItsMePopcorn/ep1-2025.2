package Services.Nucleo;

import Entities.Especialidade;
import Entities.PlanoSaude;
import Entities.Quarto;
import Persistencia.EspecialidadePersistencia;
import Persistencia.PlanoSaudePersistencia;
import Persistencia.QuartoPersistencia;

import java.util.List;

public class CadastroService {
    private List<Especialidade> especialidades;
    private List<PlanoSaude> planosDeSaude;
    private List<Quarto> quartos;

    private final QuartoPersistencia quartoPersistencia;
    private final EspecialidadePersistencia especialidadePersistencia;
    private final PlanoSaudePersistencia planoSaudePersistencia;

    public CadastroService(List<Especialidade> especialidades, List<PlanoSaude> planosDeSaude, List<Quarto> quartos, EspecialidadePersistencia especialidade, PlanoSaudePersistencia planoSaude, QuartoPersistencia quarto) {
        this.especialidades = especialidades;
        this.planosDeSaude = planosDeSaude;
        this.quartos = quartos;
        this.especialidadePersistencia = especialidade;
        this.quartoPersistencia = quarto;
        this.planoSaudePersistencia = planoSaude;
        
    }

    public Especialidade cadastrarEspecialidade(String nome) {
        for(Especialidade e : this.especialidades) {
            if(e.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Erro: Especialidade já cadastrada.");
                return null;
            }
        }
        Especialidade nova = new Especialidade(nome);
        this.especialidades.add(nova);
        System.out.println("Especialidade '" + nome + "' cadastrada.");

        especialidadePersistencia.salvar(this.especialidades);

        return nova;
    }

    public PlanoSaude cadastrarPlanoSaude(String nome, boolean internacaoGratuita) {
        for (PlanoSaude p : this.planosDeSaude) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Erro: Plano de saúde com o nome '" + nome + "' já existe.");

                return null;
            }
        }
        PlanoSaude novo = new PlanoSaude(nome, internacaoGratuita);
        this.planosDeSaude.add(novo);
        System.out.println("Plano de Saúde '" + nome + "' cadastrado.");

        planoSaudePersistencia.salvar(this.planosDeSaude);
        
        return novo;
    }

    public Quarto cadastrarQuarto(int numero) {
        for(Quarto q : this.quartos) {
            if(q.getNumero() == numero) {
                System.out.println("Erro: Quarto número " + numero + " já existe.");

                return null;
            }
        }
        Quarto novo = new Quarto(numero);
        this.quartos.add(novo);
        System.out.println("Quarto " + numero + " cadastrado.");
        quartoPersistencia.salvar(this.quartos);
        return novo;
    }
}