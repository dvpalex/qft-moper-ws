package br.com.ninb.moper.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.service.LayoutService;
import br.com.ninb.moper.service.LayoutTypeService;
import static br.com.ninb.moper.util.JSFUtils.pop;
import static br.com.ninb.moper.util.JSFUtils.push;


@ManagedBean(name="layoutBean")
@SessionScoped
@Component
public class LayoutBean implements Serializable {
	
	private static final long serialVersionUID = -7492988458428054716L;

	protected List<Layout> list;
	private Layout instance = new Layout();
		
	@Autowired
	protected LayoutService service;
	
	private LayoutType layoutType;

	public void save(){
		Boolean isNew = (instance.getLayoutId() == null);
		service.save(instance);
		pop();
	}
	
	
	public void list(){
		push("/pages/private/layout/list");
		this.list = service.list(layoutType.getLayoutTypeId());
	}
	
	public void view(){
		push("/pages/private/layout/list");
	}
	

	public List<Layout> getList() {
		return list;
	}

	public void setList(List<Layout> list) {
		this.list = list;
	}

	public Layout getInstance() {
		return instance;
	}

	public void setInstance(Layout instance) {
		this.instance = instance;
	}


	public LayoutType getLayoutType() {
		return layoutType;
	}


	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}
	
	
	
}
