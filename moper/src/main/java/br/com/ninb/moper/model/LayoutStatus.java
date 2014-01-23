package br.com.ninb.moper.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LAYOUTSTATUS")
public class LayoutStatus implements Serializable 
{
	private static final long serialVersionUID = -2495404439149186612L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAYOUTSTATUS_ID")
	private Long layoutStatusId;
	
	@Column(name = "COLNAME", nullable = false, length = 80)
	private String name;
	
	@Column(name = "DESCR", nullable = false, length = 80)
	private String descr;

	public Long getLayoutStatusId() {
		return layoutStatusId;
	}

	public void setLayoutStatusId(Long layoutStatusId) {
		this.layoutStatusId = layoutStatusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (layoutStatusId ^ (layoutStatusId >>> 32));
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
		LayoutStatus other = (LayoutStatus) obj;
		if (layoutStatusId != other.layoutStatusId)
			return false;
		return true;
	}
}
