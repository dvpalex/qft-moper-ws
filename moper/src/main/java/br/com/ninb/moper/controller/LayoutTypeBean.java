package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.service.LayoutTypeService;

//@ManagedBean(name="layoutTypeBean")
//@SessionScoped
//@Component
public class LayoutTypeBean {

	private List<LayoutType> list = new ArrayList<LayoutType>();
	private LayoutType instance = new LayoutType();

	//@Autowired
	private LayoutTypeService service;
	
	
	
	public List<LayoutType> getList() {
		return list;
	}


	public void setList(List<LayoutType> list) {
		this.list = list;
	}


	public LayoutType getInstance() {
		return instance;
	}



	public void setInstance(LayoutType instance) {
		this.instance = instance;
	}



	public void save(){
		try{
			service.save(instance);
			System.out.println("Salvar");
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}


	public void list(){
		push("/pages/private/layouttype/list");
		List<LayoutType> layouts = service.list();
		list = layouts;
	}
	
	
	public void add(){
		push("/pages/private/layouttype/new");
		this.instance = new LayoutType();
	}
	
	
	
}
