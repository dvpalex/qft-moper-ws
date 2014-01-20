package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.service.LayoutService;
import br.com.ninb.moper.service.LayoutTypeService;

@ManagedBean(name="layoutBean")
@SessionScoped
@Component
public class LayoutBean 
{
	private Layout layout;
	private List<Layout> layouts;
	@Autowired
	private LayoutService service;
	@Autowired
	private LayoutTypeService serviceType;
	private Map<String, Long> types = new HashMap<String, Long>();
	
	public LayoutBean() {
		super();
	}

	public void add()
	{	
		layout = new Layout();
		layout.setLayoutType(new LayoutType());
		push("/pages/private/layout/new");
	}
	
	public void save()
	{	
		service.save(layout);	
		layout = new Layout();
		layout.setLayoutType(new LayoutType());
		list();
	}
	
	public void delete()
	{
		service.delete(layout);
		list();
	}
	
	public void search()
	{
		if(layout.getLayoutType().getLayoutTypeId() == 0 && layout.getColName() == ""){
			list();
		}else{		
				layouts = service.listByLayout(layout);
				push("/pages/private/layout/list");
		}	
	}
	
	public void list()
	{
		layouts = service.listAll();
		layout = new Layout();
		layout.setLayoutType(new LayoutType());
		
		for(LayoutType type : serviceType.listAll()){
			types.put(type.getName(),type.getLayoutTypeId());
		}
		
		push("/pages/private/layout/list");
	}

	public void edit() 
	{ 
		push("/pages/private/layout/edit");
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	public LayoutService getService() {
		return service;
	}

	public void setService(LayoutService service) {
		this.service = service;
	}

	public LayoutTypeService getServiceType() {
		return serviceType;
	}

	public void setServiceType(LayoutTypeService serviceType) {
		this.serviceType = serviceType;
	}

	public Map<String, Long> getTypes() {
		return types;
	}

	public void setTypes(Map<String, Long> types) {
		this.types = types;
	}
}