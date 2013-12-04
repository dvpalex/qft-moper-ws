package br.com.ninb.moper.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ninb.moper.model.PFisica;
import br.com.ninb.moper.model.PJuridica;
import br.com.ninb.moper.model.Pessoa;
import br.com.ninb.moper.model.TipoPessoaEnum;

@ManagedBean
@SessionScoped
public class PessoaMB implements Serializable {


	private List<Pessoa> list;
	private int id = 10;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private static final long serialVersionUID = 1L;
	

	public PessoaMB(){
		
	
		System.out.println("Fui Invocado");
	
	}


	public List<Pessoa> getList() {
		 list = new ArrayList<Pessoa>();
			
		 this.setId(33);
		 
			Pessoa oPessoa = new PFisica(); 
			oPessoa.setId(1);
			oPessoa.setDtaInclusao(new Date());	
			oPessoa.setTipoPessoa(TipoPessoaEnum.FISICA);
			list.add(oPessoa);
	
			Pessoa oPessoa1 = new PJuridica(); 
			oPessoa1.setId(1);
			oPessoa1.setDtaInclusao(new Date());	
			oPessoa1.setTipoPessoa(TipoPessoaEnum.JURIDICA);
			list.add(oPessoa1);
			
			
		return list;
	}


	public void setList(List<Pessoa> list) {
			
		
		this.list = list;
	}
	
	
	

	
		
}
