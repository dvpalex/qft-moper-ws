package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;
import static br.com.ninb.moper.util.JSFUtils.pop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.service.LayoutTypeService;

@ManagedBean(name="layoutTypeBean")
@SessionScoped
@Component
public class LayoutTypeBean {

	private List<LayoutType> list = new ArrayList<LayoutType>();
	private LayoutType instance = new LayoutType();
	private String description;
	
	
	@Autowired
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
			Boolean isNew = (instance.getLayoutTypeId() == null);
			service.save(instance);
			pop();
			
			//if(isNew) pop();
			
			
		}catch(Exception e){
			
			System.out.println(e.toString());
		}
	}


	public void list(){
		push("/pages/private/layouttype/list");
		List<LayoutType> layouts = service.list();
		list = layouts;
	}
	
	public void view() {
		this.instance = service.getById(this.instance.getLayoutTypeId());
		push("/pages/private/layouttype/view");
	}
	
	
	public void add(){
		push("/pages/private/layouttype/new");
		this.instance = new LayoutType();
	}
	
	public void edit(){
		push("/pages/private/layouttype/edit");
		
	}
	
	public void remove(){
		service.remove(instance);
		pop();
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	public void search() {
		Map<String, Object> filters = new HashMap<String, Object>();

		if (description!=null && !description.isEmpty()) filters.put("description", "%" + description + "%");
		list = service.find(filters);
		
		/*
		if (list.isEmpty()) 
			JSFUtils.addInfoMessage(getMessage("message.global.success"), getMessage("message.job.empty.list"));
	
		*/
	}
	
	public void cancel() {
		pop();
	}
	
}
