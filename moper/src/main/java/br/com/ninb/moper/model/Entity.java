package br.com.ninb.moper.model;

import java.util.Date;

public abstract class Entity {


	private Date dtaInclusao;
	private long idUser;
	
	public Date getDtaInclusao() {
		return dtaInclusao;
	}
	public void setDtaInclusao(Date dtaInclusao) {
		this.dtaInclusao = dtaInclusao;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	
}
