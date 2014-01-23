package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.layout.LayoutUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;
import br.com.ninb.moper.model.RowType;
import br.com.ninb.moper.service.LayoutService;
import br.com.ninb.moper.service.LayoutTypeService;
import br.com.ninb.moper.service.LayoutVersionService;
import br.com.ninb.moper.service.RowTypeService;
import br.com.ninb.moper.util.LayoutUtil;

@ManagedBean(name="layoutBean")
@SessionScoped
@Component
public class LayoutBean extends LayoutGeneric
{
	@Autowired
	private LayoutService service;
	@Autowired
	private LayoutTypeService serviceType;
	@Autowired
	private LayoutVersionService serviceVersion;
	@Autowired
	private RowTypeService serviceRowType;

	public LayoutBean() {
		super();
	}
		
	public void create()
	{	
		layout = new Layout();
		layout.setLayoutVersion(new LayoutVersion());
		layout.getLayoutVersion().setLayoutType(new LayoutType());
		layout.setRowType(new RowType());
		push("/pages/private/layout/create");
	}
		
	public void newLayout()
	{	
		if(serviceVersion.listByLayoutTypeId(layout.getLayoutVersion().getLayoutType().getLayoutTypeId()).size() == 0)
		{
			layout.getLayoutVersion().setLayoutType(serviceType.selectById(layout.getLayoutVersion().getLayoutType().getLayoutTypeId()));
			layout.setRowType(new RowType());
			layouts = new ArrayList<Layout>();
			push("/pages/private/layout/new");
			
		}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Já existe uma versão para o Layout Type escolhido."));
				push("/pages/private/layout/create");
		}
	}
	
	public void add()
	{	
		boolean isValid = true;
			
		if(layout.getLenghtField() < 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "O campo lenght não pode ser negativo."));
			isValid = false;
					
		}else if(layout.getBeginField() == layout.getEndField() || layout.getBeginField() == 0 || layout.getEndField() == 0){		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Os campos initial e final comtém valores não permitidos."));
			isValid = false;
		
		}else if(layout.getIndexField() <= 0){			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "O campo index contém valor inválido."));
			isValid = false;
		
		}else{	
				for(SelectItem item : getLayoutTypes()){
					if(item.getValue() == layout.getLayoutVersion().getLayoutType().getLayoutTypeId()){
						layout.getLayoutVersion().getLayoutType().setName(item.getLabel());
						break;
					}
				}	
		
				for(SelectItem item : getRowTypes()){
					if(item.getValue() == layout.getRowType().getRowTypeId()){
						layout.getRowType().setDescr(item.getLabel());
						break;
					}
				}
			
				for(Layout layout : layouts){
					if(layout.getIndexField() == this.layout.getIndexField() && layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId()){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Index já utilizado."));
						isValid = false;
						break;
					}else if(layout.getBeginField() <= this.layout.getBeginField() && layout.getEndField() >= this.layout.getEndField()){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Os campos initial e final comtém valores já utilizados."));
						isValid = false;
						break;
					}
				}
		
				if(isValid){
					LayoutType type = layout.getLayoutVersion().getLayoutType();				
					layouts.add(layout);
					layout = new Layout();
					layout.setLayoutVersion(new LayoutVersion());
					layout.getLayoutVersion().setLayoutType(type);
					layout.setRowType(new RowType());
				}
		}
		
		if(layout.getLayoutVersion().getLayoutVersionId() != null){
			push("/pages/private/layout/edit");
		}else{
			push("/pages/private/layout/new");
		}
	}
		
	public void save()
	{	
		try{	
				LayoutVersion layoutVersion = new LayoutVersion();
				layoutVersion.setGenerateDate(new Date());
				layoutVersion.setLayoutType(layout.getLayoutVersion().getLayoutType());			
		
				for(Layout layout : layouts)
				{
					if(layout.getLayoutVersion().getLayoutVersionId() != null){
						this.layout.setLayoutVersion(layout.getLayoutVersion());
						break;
					}
				}
			
				if(layout.getLayoutVersion().getLayoutVersionId() == null)
				{
					layoutVersion.setVersion(1L);
					layoutVersion.setDescr("Version "+layoutVersion.getVersionLayout());
	
					for(Layout layout : layouts){
						layout.setLayoutVersion(layoutVersion);
					}
							
				}else{
						layoutVersion.setVersion(layout.getLayoutVersion().getVersionLayout() + 1);
						layoutVersion.setDescr("Version "+layoutVersion.getVersionLayout());
				
						for(Layout layout : layouts){
							layout.setLayoutId(null);
							layout.setLayoutVersion(layoutVersion);
						}				
				}
		
				service.saveAll(layouts);	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "A versão "+layoutVersion.getVersionLayout()+" foi gerada para o layout type "+layoutVersion.getLayoutType().getName()+"."));	
				list();
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
		
	public void delete()
	{
		service.delete(layout);
		list();
	}
	
	
	public void deleteLogical()
	{		
		LayoutUtil util = new LayoutUtil();
		layouts = util.updateData(layouts, layout);
		
		//layouts.remove(layout);
	}
	
	public void search()
	{
		if(layout.getLayoutVersion().getLayoutType().getLayoutTypeId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout type."));
		}else if(layout.getLayoutVersion().getLayoutVersionId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout version."));
		}else{
				layouts = service.listByLayout(layout);
				push("/pages/private/layout/list");
		}	
	}
	
	public void list()
	{
		layouts = new ArrayList<Layout>();
		layout = new Layout();
		layout.setLayoutVersion(new LayoutVersion());
		layout.getLayoutVersion().setLayoutType(new LayoutType());
		layout.setRowType(new RowType());	
		push("/pages/private/layout/list");
	}

	public void edit() 
	{ 
		push("/pages/private/layout/edit");
	}
	
	public void editLayout() 
	{ 	
		layouts.remove(layout);		
		push("/pages/private/layout/edit");
	}
}