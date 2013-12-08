package br.com.ninb.moper.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

public class LoginErrorPhaseListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;

	public void beforePhase(final PhaseEvent arg0) {
		
		Exception e = (Exception) FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.get(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (e instanceof BadCredentialsException) {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.getSessionMap()
					.put(WebAttributes.AUTHENTICATION_EXCEPTION,
							null);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid","Bad Login");
			FacesContext.getCurrentInstance().addMessage("j_username", message);
			FacesContext.getCurrentInstance().addMessage("j_password", message);
		}
	}

	public void afterPhase(final PhaseEvent arg0) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}