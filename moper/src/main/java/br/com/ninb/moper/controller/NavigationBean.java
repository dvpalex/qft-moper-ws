package br.com.ninb.moper.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
@ManagedBean(name="navigationBean")
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<String> pageStack = new ArrayList<String>();
	
	public NavigationBean() {
		this.pageStack.add("/pages/private/page");
	}

	public String getCurrentPage() {
		return pageStack.get(pageStack.size()-1);
	}

	public void push(String currentPage) {
		if (!currentPage.equals(getCurrentPage()))
			this.pageStack.add(currentPage);
	}
	
	public String pop(){
		return pageStack.remove(pageStack.size()-1);
	}
	
}