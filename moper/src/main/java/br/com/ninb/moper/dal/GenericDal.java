package br.com.ninb.moper.dal;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria; 
import org.hibernate.criterion.Restrictions; 
import br.com.ninb.moper.model.*;

public class GenericDal<E extends EntityBase, I> implements IGenericDal<E, I> {

	
	public E getById(I id ){
		return null;
	}
	
	public long save(E entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(E entity) {
		// TODO Auto-generated method stub
		
	}

	public void delete(E entity) {
		// TODO Auto-generated method stub
		
	}

	public List<E> findByCriteria(E entity, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<E> findByHQL(E entity, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<E> list(E entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
