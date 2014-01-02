package br.com.ninb.moper.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ninb.moper.model.Test;

@Configurable
@Component
public class TestService {

	@PersistenceContext
	protected  EntityManager em;

	
	@Transactional
	public void save(Test test){
		
		Test oTest = new Test();
		oTest.setMenssagem("Nao");
		em.persist(oTest);
		
		if(test.getTestId() == null){
			em.persist(test);
		}else{
			em.merge(test);
		}
	}
	
	
	public List<Test> list(){
		TypedQuery<Test> query = em.createQuery("from Test", Test.class);
		return query.getResultList();
	}

	
}
