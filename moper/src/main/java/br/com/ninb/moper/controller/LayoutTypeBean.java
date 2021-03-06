package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;
import br.com.ninb.moper.service.LayoutTypeService;

@ManagedBean(name="layoutTypeBean")
@SessionScoped
@Component
public class LayoutTypeBean extends GenericBean
{
	private List<LayoutType> types;	
	
	@Autowired
	private LayoutTypeService service;
	
	public LayoutTypeBean() {
		super();
	}

	public void add()
	{	
		layoutType = new LayoutType();
		push("/pages/private/layouttype/new");
	}
		
	public void save()
	{	
		service.save(layoutType);	
		layoutType = new LayoutType();
		list();
	}
	
	public void delete()
	{
		service.delete(layoutType);
		list();
	}
	
	public void search()
	{
		types = service.listByDescription(layoutType.getDescr());
		push("/pages/private/layouttype/list");
	}
	
	public void list()
	{
		versions = layoutVersionService.listAll();
		layoutType = new LayoutType();
		push("/pages/private/layouttype/list");
	}
	
	public void edit() 
	{ 
		layouts = layoutService.listByLayoutVersion(layoutVersion);
		resetLayout(layoutVersion);		
		push("/pages/private/layout/edit");
	}
	
	public List<LayoutVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<LayoutVersion> versions) {
		this.versions = versions;
	}

	public List<LayoutType> getTypes() {
		return types;
	}

	public void setTypes(List<LayoutType> types) {
		this.types = types;
	}

	public void setService(LayoutTypeService service) {
		this.service = service;
	}
}