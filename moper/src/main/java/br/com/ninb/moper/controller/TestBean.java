package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.push;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Test;
import br.com.ninb.moper.service.TestService;

@ManagedBean
@SessionScoped
@Component
public class TestBean {

	private List<Test> list = new ArrayList<Test>();
	private Test instance = new Test();

	@Autowired
	private TestService service;
	
	
	
	public List<Test> getList() {
		return list;
	}


	public void setList(List<Test> list) {
		this.list = list;
	}


	public Test getInstance() {
		return instance;
	}



	public void setInstance(Test instance) {
		this.instance = instance;
	}

	public void save(){
		try{
			service.save(instance);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}


	public void list(){
		push("/pages/private/test/list");
		List<Test> layouts = service.list();
		list = layouts;
	}
	
	
	public void add(){
		push("/pages/private/test/new");
		this.instance = new Test();
	}

	
}
