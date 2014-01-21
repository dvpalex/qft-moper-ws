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
		  TypedQuery<Layout> query = em.createQuery("from Layout l order by l.rowType.descr", Layout.class);
		  return query.getResultList();
	}
	
	public List<Layout> listByLayoutTypeName(String name)
	{
		  TypedQuery<Layout> query = em.createQuery("from Layout l where l.layoutType.name like ? order by l.rowType.descr", Layout.class)
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
		
		if(layout.getRowType().getRowTypeId() != 0){
		sql.append("and l.rowType.rowTypeId = ? ");
		}
		
		sql.append("order by l.rowType.descr");	
		
		TypedQuery<Layout> query = em.createQuery(sql.toString(), Layout.class);
		
		query.setParameter(1, "%"+layout.getColName()+"%");
		
		if(layout.getLayoutType().getLayoutTypeId() != 0){
			query.setParameter(2, layout.getLayoutType().getLayoutTypeId());	
		}
		
		if(layout.getRowType().getRowTypeId() != 0 && layout.getLayoutType().getLayoutTypeId() != 0){
			query.setParameter(3, layout.getRowType().getRowTypeId());
		}else if(layout.getRowType().getRowTypeId() != 0 && layout.getLayoutType().getLayoutTypeId() == 0){
			query.setParameter(2, layout.getRowType().getRowTypeId());
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

	//TODO
	public boolean isValidInsert(Layout layout)
	{
		try{
			TypedQuery<Layout> query = em.createQuery("from Layout l where l.indexField = ? and l.layoutType.layoutTypeId = ? and l.layoutVersion.layoutVersionId = ?", Layout.class)
					 .setParameter(1, layout.getIndexField())
					 .setParameter(2, layout.getLayoutType().getLayoutTypeId())
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