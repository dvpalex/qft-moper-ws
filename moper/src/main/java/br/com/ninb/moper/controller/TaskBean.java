package br.com.ninb.moper.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name="task")
public class TaskBean implements Serializable {

	 private static final long serialVersionUID = 1L;
	
	@PostConstruct 
	public void init(){ 
		System.out.println(" Bean executado! "); 
	} 
	
	public String getMessage()
	{ 
		return "Primefaces Funcionando Corretamente"; 
	}



	
}
