package br.com.ninb.moper.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import br.com.ninb.moper.model.LayoutType;

@Configurable
@Component
public class LayoutTypeService 
{
	@PersistenceContext
	protected  EntityManager em;
	
	public LayoutType selectByDesc(String description)
	{
		  TypedQuery<LayoutType> query = em.createQuery("from LayoutType where descr = ?", LayoutType.class)
		 .setParameter(1, description);
		  return query.getSingleResult();
	}
	
	public LayoutType selectById(Long id)
	{
		  TypedQuery<LayoutType> query = em.createQuery("from LayoutType where layoutTypeId = ?", LayoutType.class)
		 .setParameter(1, id);
		  return query.getSingleResult();
	}
	
	public List<LayoutType> listAll()
	{
		  TypedQuery<LayoutType> query = em.createQuery("from LayoutType order by layoutTypeId desc", LayoutType.class);
		  return query.getResultList();
	}
	
	public List<LayoutType> listByDescription(String description)
	{
		  TypedQuery<LayoutType> query = em.createQuery("from LayoutType where descr like ? order by layoutTypeId desc", LayoutType.class)
		  .setParameter(1, "%"+description+"%");
		  return query.getResultList();
	}
	
	@Transactional
	public void save(LayoutType row)
	{
		if(row.getLayoutTypeId() == null){
			em.persist(row);
		}else{
			em.merge(row);
		}	  
	}
	
	@Transactional
	public void delete(LayoutType row)
	{
		em.remove(em.getReference(LayoutType.class, row.getLayoutTypeId())); 
	}
}
