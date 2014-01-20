package br.com.ninb.moper.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ninb.moper.model.RowType;

@Configurable
@Component
public class RowTypeService 
{
	@PersistenceContext
	protected  EntityManager em;
	
	public RowType selectByDesc(String description)
	{
		  TypedQuery<RowType> query = em.createQuery("from RowType where descr = ?", RowType.class)
		 .setParameter(1, description);
		  return query.getSingleResult();
	}
	
	
	public List<RowType> listAll()
	{
		  TypedQuery<RowType> query = em.createQuery("from RowType order by rowTypeId desc", RowType.class);
		  return query.getResultList();
	}
	
	public List<RowType> listByDescription(String description)
	{
		  TypedQuery<RowType> query = em.createQuery("from RowType where descr like ? order by rowTypeId desc", RowType.class)
		  .setParameter(1, "%"+description+"%");
		  return query.getResultList();
	}
	
	@Transactional
	public void save(RowType row)
	{
		if(row.getRowTypeId() == null){
			em.persist(row);
		}else{
			em.merge(row);
		}	  
	}
	
	@Transactional
	public void delete(RowType row)
	{
		em.remove(em.getReference(RowType.class, row.getRowTypeId())); 
	}
}
