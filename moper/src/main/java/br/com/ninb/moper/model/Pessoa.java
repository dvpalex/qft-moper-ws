package br.com.ninb.moper.model;

import javax.persistence.Column;
import javax.persistence.Id;

public abstract class Pessoa extends Entity {

	@Id
	private long Id;
	
	@Column(nullable=false)
	private TipoPessoaEnum tipoPessoa;
	
	
	
}
