package br.com.ninb.moper.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public abstract class Pessoa extends Entity {

	@Id
	@GeneratedValue
	
	private long Id;
	
	@Column(nullable=false)
	private TipoPessoaEnum tipoPessoa;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	
}
