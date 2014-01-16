package br.com.ninb.moper.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.OutputRowRegister;

@Configurable
@Component
public class OutputRowRegisterService
{
	@PersistenceContext
	protected  EntityManager em;
	
	public List<OutputRowRegister> listByOutptRegister(Long outputRegisterId)
	{
		  TypedQuery<OutputRowRegister> query = em.createQuery("from OutputRowRegister o where o.outputRegister.outputRegisterId = ?", OutputRowRegister.class)
		  .setParameter(1, outputRegisterId);
		  return query.getResultList();
	}
}