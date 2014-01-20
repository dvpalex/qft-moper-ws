package br.com.ninb.moper.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import br.com.ninb.moper.model.Layout;

@Configurable
@Component
public class LayoutService 
{
	@PersistenceContext
	protected  EntityManager em;
	
	public Layout selectByDesc(String description)
	{
		  TypedQuery<Layout> query = em.createQuery("from Layout where descr = ?", Layout.class)
		 .setParameter(1, description);
		  return query.getSingleResult();
	}
	
	
	public List<Layout> listAll()
	{
		  TypedQuery<Layout> query = em.createQuery("from Layout order by layoutId desc", Layout.class);
		  return query.getResultList();
	}
	
	public List<Layout> listByLayoutTypeName(String name)
	{
		  TypedQuery<Layout> query = em.createQuery("from Layout l where l.layoutType.name like ? order by layoutId desc", Layout.class)
		  .setParameter(1, "%"+name+"%");
		  return query.getResultList();
	}
	
	public List<Layout> listByLayout(Layout layout)
	{       
		StringBuilder sql = new StringBuilder();	
		sql.append("from Layout l where l.colName like ? ");		
		if(layout.getLayoutType().getLayoutTypeId() != 0){
		sql.append("and l.layoutType.layoutTypeId = ? ");
		}		
		sql.append("order by indexField asc");			
		TypedQuery<Layout> query = em.createQuery(sql.toString(), Layout.class);		
		query.setParameter(1, "%"+layout.getColName()+"%");
		if(layout.getLayoutType().getLayoutTypeId() != 0){
		query.setParameter(2, layout.getLayoutType().getLayoutTypeId());
		}
		return query.getResultList();
	}
	
	@Transactional
	public void save(Layout row)
	{
		if(row.getLayoutId() == null){
			em.persist(row);
		}else{
			em.merge(row);
		}	  
	}
	
	@Transactional
	public void delete(Layout row)
	{
		em.remove(em.getReference(Layout.class, row.getLayoutId())); 
	}
}
