package br.com.ninb.moper.service;

import java.util.ArrayList;
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
public class LayoutService {

	@PersistenceContext
	protected  EntityManager em;
	
	@Transactional
	public void save(Layout layout){
		if(layout.getLayoutId() == null){
			em.persist(layout);
		}else{
			em.merge(layout);
		}

	}
	
	
	public List<Layout> list(){
		
		List<Layout> layouts = new ArrayList<Layout>();
		
		Layout layout = new Layout();
		layout.setDesc("Layout Teste");
		layouts.add(layout);
		
		return layouts;
	}
	
	
	public List<Layout> list(Long id){
		String sql = "from Layout where LAYOUTTYPE_ID = :id ";
		TypedQuery<Layout> query = em.createQuery(sql, Layout.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	
}
