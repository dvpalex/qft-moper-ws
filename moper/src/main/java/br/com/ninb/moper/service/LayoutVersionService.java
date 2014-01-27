package br.com.ninb.moper.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import br.com.ninb.moper.model.LayoutVersion;

@Configurable
@Component
public class LayoutVersionService 
{
	@PersistenceContext
	protected EntityManager em;

	public List<LayoutVersion> listByType(Long layoutVersionId){
		TypedQuery<LayoutVersion> query = em.createQuery("from LayoutVersion l where l.layoutVersion.layoutVersionId = ?", LayoutVersion.class);
		query.setParameter(1, layoutVersionId);
		return query.getResultList();
	}
	
	public List<LayoutVersion> listByLayoutTypeId(Long layouTypeTId){
		TypedQuery<LayoutVersion> query = em.createQuery("from LayoutVersion l where l.layoutType.layoutTypeId = ? order by l.generateDate", LayoutVersion.class);
		query.setParameter(1, layouTypeTId);
		return query.getResultList();
	}
	
	public LayoutVersion selectById(Long id)
	{
		  TypedQuery<LayoutVersion> query = em.createQuery("from LayoutVersion where layoutVersionId = ?", LayoutVersion.class)
		 .setParameter(1, id);
		  return query.getSingleResult();
	}
	
	public LayoutVersion selectByDesc(String description)
	{
		  TypedQuery<LayoutVersion> query = em.createQuery("from LayoutVersion where descr = ?", LayoutVersion.class)
		 .setParameter(1, description);
		  return query.getSingleResult();
	}
	
	public List<LayoutVersion> listAll()
	{
		  TypedQuery<LayoutVersion> query = em.createQuery("from LayoutVersion order by layoutVersionId desc", LayoutVersion.class);
		  return query.getResultList();
	}
	
	public List<LayoutVersion> listByDescription(String description)
	{
		  TypedQuery<LayoutVersion> query = em.createQuery("from LayoutVersion where descr like ? order by layoutVersionId desc", LayoutVersion.class)
		  .setParameter(1, "%"+description+"%");
		  return query.getResultList();
	}
	
	@Transactional
	public void save(LayoutVersion row)
	{
		if(row.getLayoutVersionId() == null){
			em.persist(row);
		}else{
			em.merge(row);
		}	  
	}
	
	@Transactional
	public void delete(LayoutVersion row)
	{
		em.remove(em.getReference(LayoutVersion.class, row.getLayoutVersionId())); 
	}
}