package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.model.LayoutType;
import br.com.ninb.moper.model.LayoutVersion;
import br.com.ninb.moper.util.LayoutUtil;

@ManagedBean(name="layoutBean")
@SessionScoped
@Component
public class LayoutBean extends GenericBean
{
	private int index = 0;
	
	public LayoutBean() 
	{
		super();
	}
	
	/* Direcionar para a tela de cadastro de layout */
	public void configureLayout()
	{	
		try{	
				layoutVersion = new LayoutVersion();
				layoutVersion.setVersion(1L);;
				layoutVersion.setLayoutType(layoutType);
				//TODO - Definir a logica de geração da descricao
				layoutVersion.setDescr("TESTE");
				resetLayout(layoutVersion);
				layouts = new ArrayList<Layout>();
				push("/pages/private/layout/new");
				
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	/* Editar layout */
	@SuppressWarnings("static-access")
	public void editLayout()
	{
		try{
				for(Layout layout : layouts)
				{
					if(layout.getIndexField() == this.layout.getIndexField() && layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId()){
						layouts.remove(index);
						break;
					}
					
					index++;
				}
			
				layouts.remove(layout);
			
				if(layoutVersion.getLayoutVersionId() != null){
					push("/pages/private/layout/edit");
				}else{
					push("/pages/private/layout/new");
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
			push("/pages/private/layout/edit");
		}
	}
		
	/* Adicionar layout */
	public void addLayout()
	{
		try{
				/* Validar layout */
				if(isValidLayout()){
					
					layout.setRowType(rowTypeService.selectById(layout.getRowType().getRowTypeId()));
					layout.setAtivo(1);
					
					/* Verifica se deve gerar a primeira versão */
					if(layout.getLayoutVersion().getLayoutVersionId() == null){
						layout.getLayoutVersion().setVersion(1L);
					}
									
					/* Adicionando na lista de layouts */
					layouts.add(index,layout);				
					
					/* Organizando os valores begin e end */												
					layouts = new LayoutUtil().updateData(layouts, layout);
					
					resetLayout(layoutVersion);	
				}

				/* Verificar se está criando uma nova versão ou editado */
				if(layout.getLayoutVersion().getLayoutVersionId() != null){
					push("/pages/private/layout/edit");
				} else {
					push("/pages/private/layout/new");
				}			
			
		}catch(Exception ex){
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Erro ao adicionar Layout!"));  

		}finally{
			index = 0;
		}
	}
	
	/* Salvar layout */
	public void saveLayout()
	{
		try{
				/* Setando o status ATIVO */
				for(Layout layout : layouts){
					layout.setAtivo(1);
				}
			
				/* Salvar layouts */
				layoutService.saveAll(layouts);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Layout type salvo com sucesso!"));  
			
		}catch(Exception ex){
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Ocorreu um erro ao salvar o layout type!"));  
		}finally{
			push("/pages/private/layout/new");
		}
	}
	
	/* Atualizar layout */
	public void updateVersion()
	{
		try{
				for(Layout layout : layouts){
					if(layout.getLayoutId() == null){
					layout.setLayoutVersion(layoutVersion);
					}
				}
				
				/* Salvar layouts */
				layoutService.saveAll(layouts);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Layout type atualizado com sucesso!"));
			
		}catch(Exception ex){
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Ocorreu um erro ao atualizar o layout type!"));  
		}finally{
			push("/pages/private/layout/edit");
		}
	}
	
	/* Salvar layout */
	public void saveNewVersion()
	{
		try{
				layoutVersion.setLayoutVersionId(null);
				layoutVersion.setVersion(layout.getLayoutVersion().getVersionLayout() + 1);;
				layoutVersion.setLayoutType(layout.getLayoutVersion().getLayoutType());
				//TODO - Definir a logica de geração da descricao
				layoutVersion.setDescr("TESTE");
			
				for(Layout layout : layouts){
					layout.setLayoutId(null);
					layout.setLayoutVersion(layoutVersion);
				}
				
				/* Salvar layouts */
				layoutService.saveAll(layouts);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Layout type salvo com sucesso!"));  
				push("/pages/private/layout/new");
			
		}catch(Exception ex){
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Ocorreu um erro ao salvar o layout type!"));  
		}
	}
		
	//TODO - Passar para outra classe
	@SuppressWarnings("static-access")
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
					if((this.layout.getIndexField() == layout.getIndexField()) && (layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId()) && layout.getAtivo() == 1)
					{
						context.addMessage(null, new FacesMessage("Warnning", "Index já utilizado!"));
						return false;
					}				
					/*Validar se o intervalo é permitido 
					if(layout.getEndField() >= this.layout.getBeginField() && (layout.getRowType().getRowTypeId() == this.layout.getRowType().getRowTypeId()))
					{
						context.addMessage(null, new FacesMessage("Warnning", "Os campos initial e final comtém valores já utilizados!"));
						return false;
					}*/
				}
				
				return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	/* Delete layout */
	public void deleteLayout()
	{		
		LayoutUtil util = new LayoutUtil();
		layout.setAtivo(0);
		layouts = util.updateDataByDelete(layouts, layout);		
		resetLayout(layoutVersion);
	}

	/* Delete layout fisicamente */
	public void delete()
	{
		try{
				/* Listar layouts */
				layouts = layoutService.listByLayoutVersion(layoutVersion);	
				/* Pagar informações do layout a ser excluido */
				layoutVersion = layoutVersionService.selectById(layoutVersion.getLayoutVersionId());
				layoutVersion.setLayouts(layouts);
				/* Excluir Layout Version */
				layoutVersionService.delete(layoutVersion);
				versions = layoutVersionService.listAll();
				layoutType = new LayoutType();
				push("/pages/private/layouttype/list");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Layout Type excluído com sucesso!"));  

		}catch(Exception ex){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aviso", "Erro ao excluir Layout Type!"));  

		}
	}
}