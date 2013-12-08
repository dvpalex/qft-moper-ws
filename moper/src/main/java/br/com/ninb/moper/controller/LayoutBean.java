package br.com.ninb.moper.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ninb.moper.model.Layout;
import br.com.ninb.moper.service.LayoutService;
import static br.com.ninb.moper.util.JSFUtils.push;


@ManagedBean(name="LayoutBean")
@SessionScoped
@Component
public class LayoutBean {

	
	protected List<Layout> list;
	
	@Autowired
	protected LayoutService service;
	
	
	public void list(){
		list = service.list();
		push("/pages/private/layoutlist/list");
		

	}
	
	
}
