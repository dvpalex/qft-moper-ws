
package br.com.ninb.moper.util;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class PrimefacesExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public PrimefacesExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterable<ExceptionQueuedEvent> events = this.wrapped.getUnhandledExceptionQueuedEvents();
        for(Iterator<ExceptionQueuedEvent> it = events.iterator(); it.hasNext();) {
            ExceptionQueuedEvent event = it.next();
            ExceptionQueuedEventContext eqec = event.getContext();
            
            if(eqec.getException() instanceof ViewExpiredException) {
                FacesContext context = eqec.getContext();
                NavigationHandler navHandler = context.getApplication().getNavigationHandler();
 
                try {
                    navHandler.handleNavigation(context, null, "home?faces-redirect=true&expired=true");
                }
                finally {
                    it.remove();
                }
            } else if (eqec.getException().getMessage().contains("Access is denied")){
                FacesContext context = eqec.getContext();
                NavigationHandler navHandler = context.getApplication().getNavigationHandler();
 
                try {
                	JSFUtils.addErrorMessage(JSFUtils.getMessage("message.global.error"), JSFUtils.getMessage("message.security.permission.denied"));
                    navHandler.handleNavigation(context, null, "home?faces-redirect=true&expired=true");
                }
                finally {
                    it.remove();
                }
            	
            }
        }

        this.wrapped.handle();;
    }
}
