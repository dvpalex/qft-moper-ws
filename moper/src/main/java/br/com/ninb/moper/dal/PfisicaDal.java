package br.com.ninb.moper.dal;

import br.com.ninb.moper.model.PFisica;

public class PfisicaDal {

	public void salve(PFisica pFisica){
		EntityManagerUtil.getEntityManager().getTransaction().begin();
		EntityManagerUtil.getEntityManager().persist(pFisica);
		EntityManagerUtil.getEntityManager().getTransaction().commit();
	}
	
}
