package com.ranchsorting.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor // dizemos que ela é um interceptador
@Transactional // é um interceptador da @Transactional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject EntityManager manager;

	// o invoke é chamado antes
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		boolean criador = false;

		try {
			if (!trx.isActive()) {
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit, confirmaria até mesmo operações sem
				// transação)
				trx.begin();
				trx.rollback();

				// agora sim inicia a transação
				trx.begin();

				criador = true;
			}

			return context.proceed(); // beleza, já interceptei, prossiga, pode
										// continuar.
		} catch (Exception e) {
			// se tiver exceção, faz rollback
			if (trx != null && criador) {
				trx.rollback();
			}

			throw e;
		} finally {
			// e faz commit
			if (trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}

}
