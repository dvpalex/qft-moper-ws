package br.com.ninb.moper.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import br.com.ninb.moper.model.LayoutVersion;

@Configurable
@Component
public class LayoutVersionService 
{
	@PersistenceContext
	protected EntityManager em;

	public List<LayoutVersion> listByType(Long layoutTypeId){
		TypedQuery<LayoutVersion> query = em.createQuery("from LayoutVersion l where l.layoutType.layoutTypeId = ?", LayoutVersion.class);
		query.setParameter(1, layoutTypeId);
		return query.getResultList();
	}
}