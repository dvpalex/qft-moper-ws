package br.com.ninb.moper.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.service.LayoutService;
import static br.com.ninb.moper.util.JSFUtils.push;


@ManagedBean(name="layoutBean")
@SessionScoped
@Component
public class LayoutBean implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7492988458428054716L;

	protected List<Layout> list;
	
	@Autowired
	protected LayoutService service;
	
	
	public void list(){
		
		push("/pages/private/layoutlist/list");
		
		
		List<Layout> layouts = new ArrayList<Layout>();
		
		Layout layout = new Layout();
		layout.setDescription("Layout Teste");
		layouts.add(layout);
		list = layouts;
		System.out.println("Acessado");

	}
	
	
}
