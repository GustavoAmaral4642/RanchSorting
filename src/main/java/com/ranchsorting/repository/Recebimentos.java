package com.ranchsorting.repository;

import java.io.Serializable;      
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.ranchsorting.model.Recebimento;

public class Recebimentos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	
	public Recebimento guardarRecebimentos(Recebimento recebimento){
		return manager.merge(recebimento);
	}
	
	public Recebimento recebimentoPorId(Long id) {
		return manager.find(Recebimento.class, id);
	}
	
	public List<Recebimento> todasRecebimentos() {
		try{
			return manager.createQuery("from Recebimento", Recebimento.class).getResultList();	
		}  catch (NoResultException e) {
			return null;
		}
	}
	/*
	@SuppressWarnings("unchecked")
	public List<ContasReceber> filtradas(ContasReceberFilter filtro){
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ContasReceber.class)
				.createAlias("campeonato", "c");
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(filtro.getCampeonato())){
			criteria.add(Restrictions.ilike("c.nome", filtro.getCampeonato(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getDataEventoInicial() != null) {
			criteria.add(Restrictions.ge("dataEvento", filtro.getDataEventoInicial()));
		}

		if (filtro.getDataEventoFinal() != null) {
			criteria.add(Restrictions.le("dataEvento", filtro.getDataEventoFinal()));
		}
				
		return criteria.addOrder(Order.asc("nome")).list();
	}
*/
}
