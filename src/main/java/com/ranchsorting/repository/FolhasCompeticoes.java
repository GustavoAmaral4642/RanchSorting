package com.ranchsorting.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ranchsorting.model.FolhaCompeticao;
import com.ranchsorting.repository.filter.FolhaCompeticaoFilter;

public class FolhasCompeticoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public FolhaCompeticao guardar(FolhaCompeticao folha) {
		return manager.merge(folha);
	}

	public List<FolhaCompeticao> guardarColecao(List<FolhaCompeticao> folhas) {
		
		for(FolhaCompeticao fo : folhas){
			manager.merge(fo);
		}
		return folhas;
	}
	
	public FolhaCompeticao porId(Long id) {
		return manager.find(FolhaCompeticao.class, id);
	}

	public List<FolhaCompeticao> todasFolhas() {

		return manager.createQuery("from FolhaCompeticao", FolhaCompeticao.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<FolhaCompeticao> filtradas(FolhaCompeticaoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FolhaCompeticao.class).createAlias("fichaInscricao1", "fi1");

		if (filtro.getObjCampeonato() != null) {
			criteria.add(Restrictions.eq("fi1.campeonato", filtro.getObjCampeonato()));
		}

		if (filtro.getObjEtapa() != null) {
			criteria.add(Restrictions.eq("fi1.etapa", filtro.getObjEtapa()));
		}

		if (filtro.getObjDivisao() != null) {
			criteria.add(Restrictions.eq("fi1.divisao", filtro.getObjDivisao()));
		}
		
		if (filtro.getDataCompeticaoInicial() != null) {
			criteria.add(Restrictions.ge("data", filtro.getDataCompeticaoInicial()));
		}

		if (filtro.getDataCompeticaoFinal() != null) {
			criteria.add(Restrictions.le("data", filtro.getDataCompeticaoFinal()));
		}

		return criteria.addOrder(Order.asc("fi1.campeonato")).list();
	}

	
	
}
