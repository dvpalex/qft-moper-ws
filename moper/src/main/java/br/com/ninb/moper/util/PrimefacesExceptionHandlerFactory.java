
package br.com.ninb.moper.util;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;


public class PrimefacesExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory base;
    
    public PrimefacesExceptionHandlerFactory(ExceptionHandlerFactory base) {
        this.base = base;
    }
    
    @Override
    public ExceptionHandler getExceptionHandler() {
        return new PrimefacesExceptionHandler(base.getExceptionHandler());
    }
    
}
