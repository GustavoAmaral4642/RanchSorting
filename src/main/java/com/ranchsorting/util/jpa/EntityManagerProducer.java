package com.ranchsorting.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// é para ser executado durante toda a aplicação.
// É uma fabrica criada de uma vez só.
@ApplicationScoped
public class EntityManagerProducer {
	
	// criando fábrica de EntityManager
	private EntityManagerFactory factory;
	
	// Construtor eu instancio a factory
	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("RanchSortingPU");
	}
	
	//Crio um produtor de EntityManager.
	// instancio um EntityManagerFactory
	// eu coloco um escopo nele, para dizer que o resultado da produção deste método, ele tem o escopo de requisição, ou seja, só existe durante a requisição. É um tempo curto, mas não tão curto. Tem requisição, chama ele, não tem, fecha ele.
	@Produces @RequestScoped
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}
	
	//tenho que fechar o EntityManager, não posso deixar aberto.
	// @Disposes é um gatilho quando a requisição que está armazenando o objeto, chama este método e fecha o EntityManager
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
	
}