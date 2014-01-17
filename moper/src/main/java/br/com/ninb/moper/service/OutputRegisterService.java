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

import br.com.ninb.moper.model.OutputRegister;

@Configurable
@Component
public class OutputRegisterService
{
	@PersistenceContext
	protected  EntityManager em;
	
	@Transactional
	public void save(OutputRegister outputRegister)
	{
		if(outputRegister.getOutputRegisterId() == null){
			em.persist(outputRegister);
		}else{
			em.merge(outputRegister);
		}

	}
	
	public List<OutputRegister> listByFileName(String fileName)
	{
		  TypedQuery<OutputRegister> query = em.createQuery("from OutputRegister o where o.fileName like ?", OutputRegister.class)
		  .setParameter(1, "%"+fileName+"%");
		  return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<OutputRegister> find(OutputRegister outputRegister, Date dataInicio, Date dataFim)
	{
		try{
			  StringBuilder sql = new StringBuilder("SELECT * FROM OUTPUTREGISTER WHERE  ");
			  sql.append(" LAYOUTTYPE_ID = ? ");
			  sql.append(" AND LAYOUTVERSION_ID = ? ");
			  sql.append(" AND GENERATEDATE BETWEEN ? AND ? ");		  
			  Query query = em.createNativeQuery(sql.toString(), OutputRegister.class)	
			  .setParameter(1, outputRegister.getLayoutType().getLayoutTypeId())
			  .setParameter(2, outputRegister.getLayoutVersion().getLayoutVersionId())
			  .setParameter(3, dataInicio)
			  .setParameter(4, dataFim);			  
			  return query.getResultList();
			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public List<OutputRegister> listAll()
	{
		  TypedQuery<OutputRegister> query = em.createQuery("from OutputRegister", OutputRegister.class);
		  return query.getResultList();
	}
}