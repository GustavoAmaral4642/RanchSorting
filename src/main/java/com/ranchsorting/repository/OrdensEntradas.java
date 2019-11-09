package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.Campeonato;
import com.ranchsorting.model.Competidor;
import com.ranchsorting.model.Divisao;
import com.ranchsorting.model.Etapa;
import com.ranchsorting.model.FichaInscricao;
import com.ranchsorting.model.OrdemEntrada;
import com.ranchsorting.model.Passada;
import com.ranchsorting.repository.filter.OrdemEntradaFilter;
import com.ranchsorting.service.NegocioException;

public class OrdensEntradas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public OrdemEntrada guardar(OrdemEntrada ordem) {
		return manager.merge(ordem);
	}

	public OrdemEntrada porId(Long id) {
		OrdemEntrada ordem = new OrdemEntrada();

		// try {
		Object[] vetorOrdem = (Object[]) manager.createNamedQuery("OrdemEntrada.buscarOrdemEntradaPorId")
				.setParameter("id", id).getSingleResult();

		Campeonato campeonato = new Campeonato();
		Etapa etapa = new Etapa();
		Divisao divisao = new Divisao();

		ordem = (OrdemEntrada) vetorOrdem[0];

		campeonato = (Campeonato) vetorOrdem[1];
		etapa = (Etapa) vetorOrdem[2];
		divisao = (Divisao) vetorOrdem[3];

		ordem.setCampeonato(campeonato);
		ordem.setEtapa(etapa);
		ordem.setDivisao(divisao);

		List<Passada> passadas = new ArrayList<Passada>();

		String jpql1 = "select p from Passada p where p.ordemEntrada=:id";
		String jpql2 = "select f, c from FichaInscricao f JOIN f.competidor c where f.passada=:id";
		
		passadas = manager.createQuery(jpql1, Passada.class).setParameter("id", ordem).getResultList();

		for (Passada p : passadas) {
			List<Object[]> fichas = new ArrayList<Object[]>();
			fichas = manager.createQuery(jpql2).setParameter("id", p).getResultList();			
		}

		ordem.setPassadas(passadas);

		/*
		 * } catch (NoResultException ex) { ordem = manager.find(OrdemEntrada.class,
		 * id);
		 * 
		 * } catch (Exception ex) { throw new
		 * NegocioException("Ocorreu algum promblema na consulta da Ordem de Entrada." +
		 * "Entre em contato com o administrador do Sistema."); }
		 */
		return ordem;
		// return manager.find(OrdemEntrada.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<OrdemEntrada> filtradas(OrdemEntradaFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(OrdemEntrada.class);

		/*
		 * if (filtro.getCampeonato() != null) { criteria.add(Restrictions.eq("ca.nome",
		 * filtro.getCampeonato().getNome())); }
		 * 
		 * 
		 * if (filtro.getEtapa() != null) { criteria.add(Restrictions.eq("e.nome",
		 * filtro.getEtapa().getNome())); }
		 * 
		 * if (filtro.getDivisao() != null) { criteria.add(Restrictions.eq("d.nome",
		 * filtro.getDivisao().getNome())); }
		 * 
		 * if (filtro.getDataInscricaoInicial() != null) {
		 * criteria.add(Restrictions.ge("dataInscricao",
		 * filtro.getDataInscricaoInicial())); }
		 * 
		 * if (filtro.getDataInscricaoFinal() != null) {
		 * criteria.add(Restrictions.le("dataInscricao",
		 * filtro.getDataInscricaoFinal())); }
		 */
		return criteria.addOrder(Order.asc("id")).list();
	}

	@SuppressWarnings("unchecked")
	public List<OrdemEntrada> buscarOrdensEntradasComPaginacao(int first, int pageSize, OrdemEntradaFilter filtro,
			String ordenar, String tipoOrdenacao) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(OrdemEntrada.class);
		criteria.setFetchMode("campeonato", FetchMode.JOIN).createAlias("campeonato", "ca");
		criteria.setFetchMode("etapa", FetchMode.JOIN).createAlias("etapa", "e");
		criteria.setFetchMode("divisao", FetchMode.JOIN).createAlias("divisao", "d");

		if (StringUtils.isNotBlank(filtro.getNomeCampeonato())) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getNomeCampeonato(), MatchMode.ANYWHERE));
		}

		// busca campeonatos por objeto
		if (filtro.getCampeonato() != null) {
			criteria.add(Restrictions.ilike("ca.nome", filtro.getCampeonato().getNome()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeEtapa())) {
			criteria.add(Restrictions.ilike("e.nome", filtro.getNomeEtapa(), MatchMode.ANYWHERE));
		}

		// busca etapas por objeto
		if (filtro.getEtapa() != null) {
			criteria.add(Restrictions.ilike("e.nome", filtro.getEtapa().getNome()));
		}

		if (StringUtils.isNotBlank(filtro.getNomeDivisao())) {
			criteria.add(Restrictions.ilike("d.nome", filtro.getNomeDivisao(), MatchMode.ANYWHERE));
		}

		// busca divisoes por objeto
		if (filtro.getDivisao() != null) {
			criteria.add(Restrictions.ilike("d.nome", filtro.getDivisao().getNome()));
		}

		if (filtro.getDataCompeticaoInicial() != null) {
			criteria.add(Restrictions.ge("dataCompeticao", filtro.getDataCompeticaoInicial()));
		}

		if (filtro.getDataCompeticaoFinal() != null) {
			criteria.add(Restrictions.le("dataCompeticao", filtro.getDataCompeticaoFinal()));
		}

		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		if (tipoOrdenacao.equals("decrescente")) {
			return criteria.addOrder(Order.desc(ordenar)).list();
		} else {
			return criteria.addOrder(Order.asc(ordenar)).list();
		}
	}

	public Long encontrarQuantidadeTotalDeOrdensEntradas() {
		return manager.createQuery("select count(o) from OrdemEntrada o", Long.class).getSingleResult();
	}
}
