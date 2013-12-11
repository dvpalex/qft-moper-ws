package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.getMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean {

	private MenuModel model;

	public MenuBean() {
		
		model = new DefaultMenuModel();
	
		DefaultSubMenu subMenu = null;
		DefaultMenuItem subItem = null;
		
		/* Dicionario Menu */
		
		subItem = new DefaultMenuItem("Layout");
		subItem.setIcon("ui-icon-bullet");
		subItem.setCommand("#{layoutBean.list}");
		subItem.setAjax(false);
		
		model.addElement(subItem);		
		
		/* Layout Type Menu */

		/*
		subItem = new DefaultMenuItem("Layout Type");
		subItem.setIcon("ui-icon-bullet");
		subItem.setCommand("#{jobBean.list}");
		subItem.setAjax(false);

		model.addElement(subItem);
		*/
		
		/* Output Register Menu */

		/*
		subItem = new DefaultMenuItem(getMessage("menu.schedule"));
		subItem.setIcon("ui-icon-bullet");
		subItem.setCommand("#{scheduleBean.list}");
		subItem.setAjax(false);
		
		model.addElement(subItem);
		*/
		
		//model.addElement(subMenu);


	}

	public MenuModel getModel() {
		return model;
	}

}
