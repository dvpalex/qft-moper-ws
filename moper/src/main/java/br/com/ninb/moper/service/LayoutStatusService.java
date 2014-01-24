package br.com.ninb.moper.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import br.com.ninb.moper.model.LayoutStatus;

@Configurable
@Component
public class LayoutStatusService
{
	@PersistenceContext
	protected  EntityManager em;
	
	public LayoutStatus selectByDesc(String name)
	{
		  TypedQuery<LayoutStatus> query = em.createQuery("from LayoutStatus where name = ?", LayoutStatus.class)
		 .setParameter(1, name);
		  return query.getSingleResult();
	}
}