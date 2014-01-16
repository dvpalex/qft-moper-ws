package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.OutputRegister;
import br.com.ninb.moper.service.OutputRegisterService;

@ManagedBean
@SessionScoped
@Component
public class OutputRegisterBean 
{
	private List<OutputRegister> listOutputRegister;
	
	@Autowired
	protected OutputRegisterService service;
	
	public void search(){
		push("/pages/private/outputregister/search");
		listOutputRegister = service.listAll();
	}

	public List<OutputRegister> getListOutputRegister() {
		return listOutputRegister;
	}

	public void setListOutputRegister(List<OutputRegister> listOutputRegister) {
		this.listOutputRegister = listOutputRegister;
	}
}
