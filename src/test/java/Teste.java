import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ranchsorting.model.PermissoesDeUsuario;
import com.ranchsorting.model.Tarefa;
import com.ranchsorting.model.Usuario;

public class Teste {
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("RanchSortingPU");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		Usuario usuario = new Usuario();
		usuario.setNome("Luiz Gustavo");
		usuario.setSobreNome("do Amaral Corrêa");
		usuario.setEmail("gustavo.lgac@gmail.com");
		usuario.setSenha("123456");

		PermissoesDeUsuario permissao = new PermissoesDeUsuario();
		permissao.setUsuario(usuario);
		permissao.setUsuarioAlteracao(usuario);
		permissao.setDataAlteracao(new Date());

		
		Tarefa tarefa = new Tarefa();
		tarefa.setNomePrograma("Cadastro Usuario");
		tarefa.setCodigoPrograma("/usuarios/CadastroUsuario.xhtml");
		tarefa.setPermissao(permissao);

		permissao.getTarefas().add(tarefa);
		usuario.getPermissoes().add(permissao);

		manager.persist(usuario);
		TesteCompetidor.incluirCompetidor(usuario);
		
		trx.commit();

	}
}
