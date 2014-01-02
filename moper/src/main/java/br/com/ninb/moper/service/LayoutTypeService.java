package br.com.ninb.moper.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ninb.moper.model.LayoutType;

//@Configurable
//@Component
public class LayoutTypeService {

	//@PersistenceContext
	protected EntityManager em;
	
	//@Transactional
	public void save(LayoutType layoutType){
		if(layoutType.getLayoutTypeId() == 0){
			em.persist(layoutType);
		}else{
			em.merge(layoutType);
		}
	}
	
	
	public List<LayoutType> list(){
		TypedQuery<LayoutType> query = em.createQuery("from LayoutType", LayoutType.class);
		return query.getResultList();
	}

	
	
}
