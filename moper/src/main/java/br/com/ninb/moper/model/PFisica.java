package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


//@Entity
//@Table(name="pessoa")
public class PFisica extends Pessoa implements Serializable {

	
	private static final long serialVersionUID = -8597904189759574664L;
	
	
	@Column(length=40, nullable=false)
	private String nome;
	
	@Column(length=11, nullable=false)
	private String cpf;
	
	@Column(nullable=false)
	private SexoEnum sexo;
	
	@Column(nullable=false)
	private Date dtaNascimento;
	
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	public Date getDtaNascimento() {
		return dtaNascimento;
	}
	public void setDtaNascimento(Date dtaNascimento) {
		this.dtaNascimento = dtaNascimento;
		
	
	}
	
	
	
	
}
