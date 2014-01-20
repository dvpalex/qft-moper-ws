package br.com.ninb.moper.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.springframework.stereotype.Component;

@ManagedBean(name="menuBean")
@SessionScoped
@Component
public class MenuBean {

	private MenuModel model;

	public MenuBean() {
		
		model = new DefaultMenuModel();
	
		DefaultMenuItem subItem = null;
		
		/* Dicionario Menu */
		subItem = new DefaultMenuItem("Layout Type");
		subItem.setIcon("ui-icon-bullet");
		subItem.setCommand("#{layoutTypeBean.list}");
		subItem.setAjax(false);
		
		model.addElement(subItem);
		
		subItem = new DefaultMenuItem("RowType");
		subItem.setIcon("ui-icon-bullet");
		subItem.setCommand("#{rowTypeBean.list}");
		subItem.setAjax(false);
		
		model.addElement(subItem);
		
		subItem = new DefaultMenuItem("Output Register");
		subItem.setIcon("ui-icon-bullet");
		subItem.setCommand("#{outputRegisterBean.list}");
		subItem.setAjax(false);
		
		model.addElement(subItem);
	}

	public MenuModel getModel() {
		return model;
	}

}
