package br.com.ninb.moper.controller;

import static br.com.ninb.moper.util.JSFUtils.getMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@ManagedBean(name="menuBean")
@SessionScoped
@Component
public class MenuBean {

	private MenuModel model;

	public MenuBean() {
		
		model = new DefaultMenuModel();
	
		DefaultSubMenu subMenu = null;
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
