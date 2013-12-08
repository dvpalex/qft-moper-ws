package br.com.ninb.moper.util;

import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.springframework.web.context.ContextLoader;

import br.com.ninb.moper.controller.NavigationBean;

public class JSFUtils {
	
	static ResourceBundle bundle = null;
	
	public static String getMessage(String key){
		if (bundle==null){
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msg");
		}
		return bundle.getString(key);
	}
	
	public static void addInfoMessage(String message, String detail){
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, detail));  
	}

	public static void addWarnMessage(String message, String detail){
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, detail));  
	}

	public static void addErrorMessage(String message, String detail){
        FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, detail));  
	}

	public static void addFatalMessage(String message, String detail){

        FacesContext context = FacesContext.getCurrentInstance();  
        if (context==null) return;
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, detail));  
	}
	
	public static void push(String page){
		((NavigationBean) ContextLoader.getCurrentWebApplicationContext()
				.getBean("navigationBean"))
				.push(page);		
	}
	
	public static String pop(){
		return ((NavigationBean) ContextLoader.getCurrentWebApplicationContext()
				.getBean("navigationBean"))
				.pop();		
	}
	
	public static void invalidateInput(UIInput input){
		input.setValid(false);		
	}

	public static void scanUIInput(Map<String,UIInput> inputs,UIComponent component){
		if (component instanceof UIInput){
			inputs.put(component.getId(), (UIInput) component);
		}
		for (UIComponent children: component.getChildren()){
			scanUIInput(inputs, children);
		}
	}

}
