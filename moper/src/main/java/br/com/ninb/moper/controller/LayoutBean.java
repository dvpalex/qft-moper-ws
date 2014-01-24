package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutStatus;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;
import br.com.ninb.moper.model.RowType;
import br.com.ninb.moper.util.LayoutUtil;

@ManagedBean(name="layoutBean")
@SessionScoped
@Component
public class LayoutBean extends GenericBean
{
	public LayoutBean() 
	{
		super();
	}
	
	/* Direcionar para a tela de cadastro de layout */
	public void configureLayout()
	{	
		try{
				resetLayout(layoutType);
				layouts = new ArrayList<Layout>();
				push("/pages/private/layout/new");
				
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	/* Adicionar layout */
	public void addLayout()
	{
		try{
				/* Validar layout */
				if(isValidLayout()){
					
					layout.setRowType(rowTypeService.selectById(layout.getRowType().getRowTypeId()));	
									
					/* Adicionando na lista de layouts */
					layouts.add(layout);				
					/* Setando valores */
					resetLayout(layoutType);
					
				}else{
					
				}

				push("/pages/private/layout/new");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/* Salvar layout */
	public void saveLayout()
	{
		try{
				/* Salvar layouts */
				layoutService.saveAll(layouts);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Warnning", "Layout type salvo com sucesso!"));  
				push("/pages/private/layout/new");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//TODO - Passar para outra classe
	private boolean isValidLayout()
	{
		try{
				FacesContext context = FacesContext.getCurrentInstance(); 
			
				/* Validar index */
				if(layout.getIndexField() <= 0){			
					context.addMessage(null, new FacesMessage("Warnning", "Invalid index!"));  
					return false;
				}			
				/* Validar intervalo */
				if(layout.getBeginField() == layout.getEndField() || layout.getBeginField() == 0 || layout.getEndField() == 0){		
					context.addMessage(null, new FacesMessage("Warnning", "Invalid data begin and end!"));
					return false;
				}				
				/* Validar lenght */
				if(layout.getLenghtField() < 0){
					context.addMessage(null, new FacesMessage("Warnning", "Invalid lenght!"));
					return false;
				}
				
				for(Layout layout : layouts)
				{
					/* Validar se index foi utilizado */
					if(this.layout.getLayoutId() == null && (this.layout.getIndexField() == layout.getIndexField()) && (layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId()))
					{
						context.addMessage(null, new FacesMessage("Warnning", "Index já utilizado!"));
						return false;
					}
					
					/* Validar se o intervalo é permitido */
					if(layout.getEndField() >= this.layout.getBeginField() && this.layout.getLayoutId() == null && (layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId()))
					{
						context.addMessage(null, new FacesMessage("Warnning", "Os campos initial e final comtém valores já utilizados!"));
						return false;
					}
				}
				
				return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
	
	
	public void create()
	{	
		resetLayout(new LayoutType());
		push("/pages/private/layout/create");
	}
		
	public void newLayout()
	{	
		try{
				if(layoutVersionService.listByLayoutTypeId(layout.getLayoutVersion().getLayoutType().getLayoutTypeId()).size() == 0){
					layout.getLayoutVersion().setLayoutType(layoutTypeService.selectById(layout.getLayoutVersion().getLayoutType().getLayoutTypeId()));
					layout.setRowType(new RowType());
					layouts = new ArrayList<Layout>();
					push("/pages/private/layout/new");			
				}else{
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Já existe uma versão para o Layout Type escolhido."));
						push("/pages/private/layout/create");
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	public void add()
	{	
		boolean isValidLayout = true;
		
		if(layout.getLenghtField() < 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "O campo lenght não pode ser negativo."));
			isValidLayout = false;
					
		}else if(layout.getBeginField() == layout.getEndField() || layout.getBeginField() == 0 || layout.getEndField() == 0){		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Os campos initial e final comtém valores não permitidos."));
			isValidLayout = false;
		
		}else if(layout.getIndexField() <= 0){			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "O campo index contém valor inválido."));
			isValidLayout = false;
		
		}else{	
				for(SelectItem item : getLayoutTypes())
				{
					if(item.getValue() == layout.getLayoutVersion().getLayoutType().getLayoutTypeId()){
						layout.getLayoutVersion().getLayoutType().setName(item.getLabel());
						break;
					}
				}	
				
				for(SelectItem item : getRowTypes())
				{
					if(item.getValue() == layout.getRowType().getRowTypeId()){
						layout.getRowType().setDescr(item.getLabel());
						break;
					}
				}
							
				for(Layout layout : layouts)
				{
					if(layout.getIndexField() == this.layout.getIndexField() && layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId()){
						
						/* Para os casos em que se esta editando um layout ja existente */
						if(layout.getLayoutId() != null && layout.getLayoutId() != this.layout.getLayoutId())
						{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Index já utilizado."));
							isValidLayout = false;
							break;
						}
						
					}else if(layout.getEndField() >= this.layout.getBeginField() && this.layout.getLayoutId() == null && (layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId())){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Os campos initial e final comtém valores já utilizados."));
						isValidLayout = false;
						break;
					}
				}
				
				/* Permite a inclusao / atualizacao */
				if(isValidLayout)
				{			
					/* Para layout novo : cria uma LayoutVersion e Add na lista */
					for(Layout layout : layouts){
						if(layout.getLayoutVersion().getLayoutVersionId() != null && this.layout.getLayoutId() == null){
							this.layout.setLayoutVersion(layout.getLayoutVersion());
							layouts.add(layout);
							break;
						}
					}
						
					/* Atualiza os campos begin e end se necessário */
					LayoutUtil util = new LayoutUtil();			
					layouts = util.updateDataByEdit(layouts, this.layout);					
					resetLayout(layout.getLayoutVersion().getLayoutType());
				}				
		}
		
		//if(layouts.get(0).getLayoutVersion().getLayoutVersionId() != null){
		//	push("/pages/private/layout/edit");
		//}else{
			push("/pages/private/layout/new");
		//}
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
						LayoutStatus layoutStatus = new LayoutStatus();
						layoutStatus = layoutStatusService.selectByDesc("ATIVO");
				
						for(Layout layout : layouts){
							layout.setLayoutId(null);
							layout.setLayoutVersion(layoutVersion);
							layout.setLayoutStatus(layoutStatus);
						}				
				}
		
				layoutService.saveAll(layouts);	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "A versão "+layoutVersion.getVersionLayout()+" foi gerada para o layout type "+layoutVersion.getLayoutType().getName()+"."));	
				list();
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
		
	public void delete()
	{		
		LayoutUtil util = new LayoutUtil();
		layout.setLayoutStatus(layoutStatusService.selectByDesc("INATIVO"));
		layouts = util.updateDataByDelete(layouts, layout);
	}
	
	public void search()
	{
		if(layout.getLayoutVersion().getLayoutType().getLayoutTypeId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout type."));
		}else if(layout.getLayoutVersion().getLayoutVersionId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout version."));
		}else{
				layouts = layoutService.listByLayout(layout);
				push("/pages/private/layout/list");
		}	
	}
	
	public void list()
	{
		layouts = new ArrayList<Layout>();
		resetLayout(new LayoutType());
		push("/pages/private/layout/list");
	}

	public void editLayout() 
	{ 
		if(layout.getLayoutVersion().getLayoutType().getLayoutTypeId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout type for edit."));
		}else if(layout.getLayoutVersion().getLayoutVersionId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout version for edit."));
		}else if(layout.getRowType().getRowTypeId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a row type for edit."));
		}else{		
				push("/pages/private/layout/edit");
		}
	}
	
	public void edit() 
	{ 
		if(layout.getLayoutVersion().getLayoutType().getLayoutTypeId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout type for edit."));
		}else if(layout.getLayoutVersion().getLayoutVersionId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a layout version for edit."));
		}else if(layout.getRowType().getRowTypeId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Select a row type for edit."));
		}else{	
				layouts = layoutService.listByLayout(layout);		
				push("/pages/private/layout/edit");
		}
	}

	public void update()
	{
		layoutService.saveAll(layouts);	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "A versão foi atualizada com sucesso."));	
		list();
	}
}