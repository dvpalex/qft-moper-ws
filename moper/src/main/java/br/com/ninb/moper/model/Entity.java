package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public abstract class Entity implements Serializable {

	
	
	private static final long serialVersionUID = 7108760821263160996L;

	
	@Id
	private long id;
	
	private Date dtaInclusao;
	
	@Column
	private long idUser;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dtaInclusao == null) ? 0 : dtaInclusao.hashCode());
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (dtaInclusao == null) {
			if (other.dtaInclusao != null)
				return false;
		} else if (!dtaInclusao.equals(other.dtaInclusao))
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}

}
