package br.com.ninb.moper.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.stereotype.Component;

@ManagedBean(name="menuBean")
@SessionScoped
@Component
public class MenuBean {

	private MenuModel model;

	public MenuBean() {
		
		model = new DefaultMenuModel();
	
		DefaultSubMenu subMenu = null;
		DefaultMenuItem subItem = null;
			
		subMenu = new DefaultSubMenu("Dicionário");
		subMenu.setIcon("ui-icon-bullet");
			subItem = new DefaultMenuItem("Layout Type");
			subItem.setIcon("ui-icon-bullet");
			subItem.setCommand("#{layoutTypeBean.list}");
			subItem.setAjax(false);		
		subMenu.addElement(subItem);
			subItem = new DefaultMenuItem("Row Type");
			subItem.setIcon("ui-icon-bullet");
			subItem.setCommand("#{rowTypeBean.list}");
			subItem.setAjax(false);	
		subMenu.addElement(subItem);
			subItem = new DefaultMenuItem("Output Register");
			subItem.setIcon("ui-icon-bullet");
			subItem.setCommand("#{outputRegisterBean.list}");
			subItem.setAjax(false);	
		subMenu.addElement(subItem);
	
		model.addElement(subMenu);

	}

	public MenuModel getModel() {
		return model;
	}

}
