package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Table(name="ROWTYPE")
public class RowType implements Serializable {

	private static final long serialVersionUID = -6855684724691136472L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROWTYPE_ID")
	private long rowTypeId;
	
	@Column(name = "DESCR", length = 80)
	private String descr;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rowType")
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<Layout> layouts = new ArrayList<Layout>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rowType")
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<OutputRowRegister> rows;
	
	
	
	public List<OutputRowRegister> getRows() {
		return rows;
	}

	public void setRows(List<OutputRowRegister> rows) {
		this.rows = rows;
	}

	public RowType(){
		
	}
	
	public List<Layout> getLayouts() {
		return layouts;
	}


	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	public long getRowTypeId() {
		return rowTypeId;
	}

	public void setRowTypeId(long rowTypeId) {
		this.rowTypeId = rowTypeId;
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
		result = prime * result + (int) (rowTypeId ^ (rowTypeId >>> 32));
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
		RowType other = (RowType) obj;
		if (rowTypeId != other.rowTypeId)
			return false;
		return true;
	}


	
	
	
}
