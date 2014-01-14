package br.com.ninb.moper.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.ninb.moper.util.JPAUtils;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.ninb.moper.model.LayoutType;

@Configurable
@Component
public class LayoutTypeService {

	@PersistenceContext
	protected EntityManager em;
	
	@Transactional
	public void save(LayoutType layoutType){
		if(layoutType.getLayoutTypeId() == null){
			em.persist(layoutType);
		}else{
			em.merge(layoutType);
		}
	}
	
	
	public List<LayoutType> list(){
		TypedQuery<LayoutType> query = em.createQuery("from LayoutType", LayoutType.class);
		return query.getResultList();
	}
	
	public List<LayoutType> find(Map<String, Object> filters){
	
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LayoutType> c = cb.createQuery(LayoutType.class);
		Root<LayoutType> layoutType = c.from(LayoutType.class);

		TypedQuery<LayoutType> typedQuery = em.createQuery(c.multiselect(
				layoutType.get("layoutTypeId"),
				layoutType.get("description")
			).where(JPAUtils.createQueryFromFilter(filters, cb, layoutType)));
		return typedQuery.getResultList();
	}
	
	public LayoutType getById(Long id){
		return em.find(LayoutType.class, id);
	}
	
	@Transactional
	public void remove(LayoutType layoutType){
		LayoutType instance = em.find(LayoutType.class, layoutType.getLayoutTypeId());
		em.remove(instance);
	}
	
}
