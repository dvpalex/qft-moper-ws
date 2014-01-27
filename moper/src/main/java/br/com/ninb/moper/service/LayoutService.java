package br.com.ninb.moper.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;

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
		  TypedQuery<Layout> query = em.createQuery("from Layout l order by l.rowType.descr", Layout.class);
		  return query.getResultList();
	}
	
	public List<Layout> listByLayoutTypeName(String name)
	{
		  TypedQuery<Layout> query = em.createQuery("from Layout l where l.layoutType.name like ? order by l.rowType.descr", Layout.class)
		  .setParameter(1, "%"+name+"%");
		  return query.getResultList();
	}
	
	public List<Layout> listByLayoutVersion(LayoutVersion layoutVersion)
	{
		TypedQuery<Layout> query = em.createQuery("from Layout l where l.layoutVersion.layoutType.layoutTypeId = ? and l.layoutVersion.layoutVersionId = ? order by l.indexField desc", Layout.class)
		.setParameter(1, layoutVersion.getLayoutType().getLayoutTypeId())
		.setParameter(2, layoutVersion.getLayoutVersionId());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Layout> listByLayout(Layout layout)
	{       
		StringBuilder sql = new StringBuilder();	
		sql.append("from Layout l where l.descr like ? ");
		
		if(layout.getLayoutVersion().getLayoutType().getLayoutTypeId() != 0){
			sql.append("and l.layoutVersion.layoutType.layoutTypeId = ? ");
		}
		if(layout.getRowType().getRowTypeId() != 0){
			sql.append("and l.rowType.rowTypeId = ? ");
		}	
		if(layout.getLayoutVersion().getLayoutVersionId() != 0){		
			sql.append("and l.layoutVersion.layoutVersionId = ? ");
		}

		Query query = em.createQuery(sql.toString(), Layout.class);
		
		int count = 1;
				
		query.setParameter(count, "%"+layout.getDescr()+"%");
		
		count++;
		
		if(layout.getLayoutVersion().getLayoutType().getLayoutTypeId() != 0){
			query.setParameter(count, layout.getLayoutVersion().getLayoutType().getLayoutTypeId());	
			count++;
		}	
		if(layout.getRowType().getRowTypeId() != 0){
			query.setParameter(count, layout.getRowType().getRowTypeId());
			count++;
		}		
		if(layout.getLayoutVersion().getLayoutVersionId() != 0){		
			query.setParameter(count, layout.getLayoutVersion().getLayoutVersionId());
		}
			
		return query.getResultList();
	}
	
	@Transactional
	public void save(Layout layout)
	{
		if(layout.getLayoutId() == null)
		{	
			layout.getLayoutVersion().setGenerateDate(new Date());
			
			/* Para evitar os erros de detached entity passed to persist */
			if(layout.getLayoutVersion().getLayoutType().getLayoutTypeId() != null)
			{
				LayoutType l = em.find(LayoutType.class, layout.getLayoutVersion().getLayoutType().getLayoutTypeId());
				layout.getLayoutVersion().setLayoutType(l);
				
				/* Para evitar id to load is required for loading */
				if(layout.getLayoutVersion().getLayoutVersionId() != null)
				{
					LayoutVersion v = em.find(LayoutVersion.class, layout.getLayoutVersion().getLayoutVersionId());

					if(v != null){
						layout.setLayoutVersion(v);
					}
				}
			}
			
			em.persist(layout);
						
		}else{
			em.merge(layout);
		}	  
	}
	
	@Transactional
	public void saveAll(List<Layout> layouts)
	{
		for(Layout layout : layouts)
		{		
			save(layout);
		}  
	}
	
	@Transactional
	public void delete(Layout row)
	{
		em.remove(em.getReference(Layout.class, row.getLayoutId())); 
	}

	//TODO
	public boolean isValidInsert(Layout layout)
	{
		try{
			TypedQuery<Layout> query = em.createQuery("from Layout l where l.indexField = ? and l.layoutType.layoutTypeId = ? and l.layoutVersion.layoutVersionId = ?", Layout.class)
					 .setParameter(1, layout.getIndexField())
					 .setParameter(2, layout.getLayoutVersion().getLayoutType().getLayoutTypeId())
					 .setParameter(3, layout.getLayoutVersion().getLayoutVersionId());
					  
				if(query.getSingleResult() != null){
					return true;
				}else{
					return false;
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
}