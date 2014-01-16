package br.com.ninb.moper.model;

import java.util.List;

public class CategoriaPessoa extends EntityBase {

	/*
	 * Unidade Corporativa
	 * Cliente
	 * Fornecedor
	 * Parceiro
	 * Prestador de Servicos
	 * Propect
	 * 
	 */

	private static final long serialVersionUID = -8728874922107174191L;

	private String descricao;
	
	//@ManyToMany	
	private List<Pessoa> pessoas;
	
	public CategoriaPessoa(){
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	
}
