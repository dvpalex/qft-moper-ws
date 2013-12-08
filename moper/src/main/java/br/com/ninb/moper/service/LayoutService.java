package br.com.ninb.moper.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;


@NamedQueries({
	@NamedQuery(name="lstLayout", query= ""
				
			)
})


@Configurable
@Component
public class LayoutService {

	@PersistenceContext
	protected  EntityManager em;
	
	
	public List<Layout> list(){
		
		List<Layout> layouts = new ArrayList<Layout>();
		
		Layout layout = new Layout();
		layout.setDescription("Layout Teste");
		layouts.add(layout);
		
		return layouts;
	}
	
	
}
