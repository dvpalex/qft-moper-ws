package br.com.ninb.moper.util;

import java.util.Date;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JPAUtils {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Predicate[] createQueryFromFilter(Map<String, Object> filtros, CriteriaBuilder cb, Object root) {

		int tamanho = filtros.entrySet().size();
		
		if (root instanceof Root){
			
			Root<Class> generic = (Root<Class>) root;
	
			Predicate[] predicate = new Predicate[tamanho];
			Object object = null;
			String string = null;
	
			int i = 0;
			for (Map.Entry<String, Object> valor : filtros.entrySet()) {
				
				object = valor.getValue();
				
				if (object == null) {
				
					predicate[i] = cb.isNull(generic.get((String) valor.getKey()));
					
				} else if (object instanceof String){
					string = (String) object;
					if (string.startsWith("%") || string.endsWith("%")){
						predicate[i] = cb.like(generic.<String>get((String) valor.getKey()), string);
					} else {
						predicate[i] = cb.equal(generic.get((String) valor.getKey()), string);
					}
				} else if (object instanceof Date[]){
					Date[] array = (Date[]) object;
					if (array.length == 2) {
						Date start = array[0];
						Date end = array[1];
						predicate[i] = cb.between(generic.<Date>get((String) valor.getKey()), start, end);
					}
				} else {
					predicate[i] = cb.equal(generic.get((String) valor.getKey()), object);
				}
				i++;
				
	 		}
			
			return predicate;
			
		} else {
			
			Join<Class, Class> generic = (Join<Class, Class>) root;
	
			Predicate[] predicate = new Predicate[tamanho];
			Object object = null;
			String string = null;
	
			int i = 0;
			for (Map.Entry<String, Object> valor : filtros.entrySet()) {
				
				object = valor.getValue();
				
				if (object instanceof String){
					string = (String) object;
					if (string.startsWith("%") || string.endsWith("%")){
						predicate[i] = cb.like(generic.<String>get((String) valor.getKey()), string);
					} else {
						predicate[i] = cb.equal(generic.get((String) valor.getKey()), string);
					}				
				} else {
					predicate[i] = cb.equal(generic.get((String) valor.getKey()), object);
				}
				i++;
				
	 		}
			
			return predicate;
			
		}
		
	}

}