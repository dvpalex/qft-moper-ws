package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public abstract class EntityBase implements Serializable {

	private static final long serialVersionUID = 7108760821263160996L;

	private Date dtaInclusao;
	
	@Column
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
