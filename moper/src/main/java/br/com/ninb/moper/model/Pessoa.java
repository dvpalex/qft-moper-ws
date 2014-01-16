package br.com.ninb.moper.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public  class Pessoa extends EntityBase {

	private static final long serialVersionUID = -4890518737027780299L;

	public Pessoa(){};
	
	@Id
	@GeneratedValue
	private long Id;
	
	
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private TipoPessoaEnum tipoPessoa;
	
	private String propriedadeTeste = "Propriedade Teste para :" + this.getClass().getName();
	

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

	public String getPropriedadeTeste() {
		return propriedadeTeste;
	}

	public void setPropriedadeTeste(String propriedadeTeste) {
		this.propriedadeTeste = propriedadeTeste;
	}
	
	
	
	
}
