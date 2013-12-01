package br.com.ninb.moper.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.ninb.moper.bll.PFisicaBll;
import br.com.ninb.moper.model.PFisica;

@ManagedBean(name="PFisicaMBean")
@SessionScoped
public class PFisicaMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6157873495860169047L;
	private PFisica pfisica;
	
	public PFisicaMBean(){
		this.pfisica = new PFisica();
		System.out.println("Bean PFisica Acessado");
	}
	
	public PFisica getPfisica() {
		return pfisica;
	}

	public void setPfisica(PFisica pfisica) {
		this.pfisica = pfisica;
	}

	public void iniciarPFisica(){
		this.pfisica = new PFisica();
	}
	
	public void salvar(){
		
		FacesContext facesContex = FacesContext.getCurrentInstance();
		try{
		
		PFisicaBll pFisicaBll = new PFisicaBll();
		pFisicaBll.salvar(this.pfisica);
		
		facesContex.addMessage(null, new FacesMessage("Cliente " + this.pfisica.getNome() + "Cadastrado com sucesso!"));
		
		System.out.println("Pessoa Salva : " + this.pfisica.getNome() );
		}catch(Exception ex){
			
			FacesMessage msn = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getLocalizedMessage());
			facesContex.addMessage(null, msn);
		}
	}
		
	
	
}
