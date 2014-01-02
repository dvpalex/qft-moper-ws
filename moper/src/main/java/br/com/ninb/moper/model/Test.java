package br.com.ninb.moper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TEST")
public class Test implements Serializable {

	
	private static final long serialVersionUID = 6087692050919431973L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEST_ID")
	private Integer testId;
	
	@Column(name = "MENSSAGEM", nullable = false , unique = true)
	private String Menssagem;
	
	
	
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	public String getMenssagem() {
		return Menssagem;
	}
	public void setMenssagem(String menssagem) {
		Menssagem = menssagem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testId == null) ? 0 : testId.hashCode());
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
		Test other = (Test) obj;
		if (testId == null) {
			if (other.testId != null)
				return false;
		} else if (!testId.equals(other.testId))
			return false;
		return true;
	}
	
	
	
	
	
	
}
