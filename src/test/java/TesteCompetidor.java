import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ranchsorting.model.Animal;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Etnia;
import com.ranchsorting.model.Ocorrencia;
import com.ranchsorting.model.TipoAnuidade;
import com.ranchsorting.model.Usuario;

public class TesteCompetidor {
	
	public static void incluirCompetidor(Usuario usuario) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RanchSortingPU");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();

		trx.begin();
		
		Competidor competidor = new Competidor();
		competidor.setNome("Paulo Roberto Arrais Serodio");
		competidor.setDataNascimento(new Date());
		competidor.setIdade(new Long(45));
		competidor.setResponsavel("N/A");
		competidor.setDocResponsavel("N/A");
		competidor.setContato("Telefone (19) 9 9222 2222");
		competidor.setEtnia(Etnia.MASCULINO);
		competidor.setValorPagoAnuidade(BigDecimal.valueOf(100));
		competidor.setDataPagamentoAnuidade(new Date());
		competidor.setTipoAnuidade(TipoAnuidade.TOTAL);
		competidor.setUsuarioAlteracao(usuario);
		competidor.setDataAlteracao(new Date());
		
		
		Animal animal = new Animal();
		animal.setNome("Rojo");
		animal.setIdade(new Long(2));
		animal.setEtnia(Etnia.MACHO);
		animal.setRaca("Manga Larga");
		animal.setProprietario(competidor);
		animal.setUsuarioAlteracao(usuario);
		animal.setDataAlteracao(new Date());
		
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setData(new Date());
		ocorrencia.setHora(new Date());
		ocorrencia.setDescricao("Registrando ocorrencia de " + competidor.getNome());
		ocorrencia.setPrograma("Teste Competidor");
		ocorrencia.setUsuario(usuario);
		
		competidor.setOcorrencia(ocorrencia);
		animal.setOcorrencia(ocorrencia);
		
		competidor.getAnimais().add(animal);
		
		manager.persist(competidor);

		trx.commit();

	}
}
