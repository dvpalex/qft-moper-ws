package br.com.ninb.moper.dal;
 
import java.util.List;

import br.com.ninb.moper.model.*;

public interface IGenericDal<E extends Entity> {

	public long save(E entity);
	public void update(E entity);
	public void delete(E entity);
	public List<E> findByCriteria(E entity, String value);
	public List<E> findByHQL(E entity, String value);
	public List<E> list(E entity);
}
