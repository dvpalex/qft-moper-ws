package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;
import br.com.ninb.moper.service.LayoutTypeService;
import br.com.ninb.moper.service.LayoutVersionService;

@ManagedBean(name="layoutVersionBean")
@SessionScoped
@Component
public class LayoutVersionBean 
{
	private LayoutVersion layoutVersion;
	private List<LayoutVersion> versions;
	@Autowired
	private LayoutVersionService service;
	@Autowired
	private LayoutTypeService serviceType;
	private Map<String, Long> types = new HashMap<String, Long>();
	
	public LayoutVersionBean() {
		super();
	}

	public void add()
	{	
		layoutVersion = new LayoutVersion();
		layoutVersion.setLayoutType(new LayoutType());
		push("/pages/private/layoutversion/new");
	}
	
	public void save()
	{	
		layoutVersion.setGenerateDate(new Date());
		service.save(layoutVersion);	
		layoutVersion = new LayoutVersion();
		layoutVersion.setLayoutType(new LayoutType());
		list();
	}
	
	public void delete()
	{
		service.delete(layoutVersion);
		list();
	}
	
	public void search()
	{
		versions = service.listByDescription(layoutVersion.getDescr());
		push("/pages/private/layoutversion/list");
	}
	
	public void list()
	{
		versions = service.listAll();
		layoutVersion = new LayoutVersion();
		layoutVersion.setLayoutType(new LayoutType());
		
		for(LayoutType type : serviceType.listAll()){
			types.put(type.getName(),type.getLayoutTypeId());
		}
			
		push("/pages/private/layoutversion/list");
	}
	
	public void edit() 
	{ 
		push("/pages/private/layoutversion/edit");
	}

	public LayoutVersion getLayoutVersion() {
		return layoutVersion;
	}

	public void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	public List<LayoutVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<LayoutVersion> versions) {
		this.versions = versions;
	}

	public LayoutVersionService getService() {
		return service;
	}

	public void setService(LayoutVersionService service) {
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