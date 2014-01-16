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
@Table(name="LAYOUTTYPE")
public class LayoutType implements Serializable{

	private static final long serialVersionUID = -5656501350618082518L;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAYOUTYPE_ID")
	private Long  layoutTypeId;
	
	@Column(name = "COLNAME", nullable = false, length = 80)
	private String colName;
	
    @Column(name = "DESCRIPTION", nullable = false, length = 80)
	private String description;
	
    
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "layoutType")
    @LazyCollection(LazyCollectionOption.TRUE)
	private List<LayoutVersion> layoutVersions = new ArrayList<LayoutVersion>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "layoutType")
    @LazyCollection(LazyCollectionOption.TRUE)
	private List<OutputRegister> outputRegisters = new ArrayList<OutputRegister>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "layoutType")
    @LazyCollection(LazyCollectionOption.TRUE)
	private List<Layout> layouts = new ArrayList<Layout>();

	
	public LayoutType(){
		super();
	}
	
	public LayoutType(Long layoutTypeId,  String description){
		super();
		this.layoutTypeId = layoutTypeId;
		this.description = description;
	}
	
	public LayoutType(String description){
		super();
		this.description = description;
	}
	
	public List<OutputRegister> getOutputRegisters() {
		return outputRegisters;
	}

	public void setOutputRegisters(List<OutputRegister> outputRegisters) {
		this.outputRegisters = outputRegisters;
	}

	
	
	public List<LayoutVersion> getLayoutVersions() {
		return layoutVersions;
	}


	public void setLayoutVersions(List<LayoutVersion> layoutVersions) {
		this.layoutVersions = layoutVersions;
	}


	public List<Layout> getLayouts() {
		return layouts;
	}


	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}


	public Long getLayoutTypeId() {
		return layoutTypeId;
	}

	public void setLayoutTypeId(Long layoutTypeId) {
		this.layoutTypeId = layoutTypeId;
	}

	public List<LayoutVersion> getLayoutVersiona() {
		return layoutVersions;
	}



	public void setLayoutVersiona(List<LayoutVersion> layoutVersions) {
		this.layoutVersions = layoutVersions;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (layoutTypeId ^ (layoutTypeId >>> 32));
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
		LayoutType other = (LayoutType) obj;
		if (layoutTypeId != other.layoutTypeId)
			return false;
		return true;
	}

	
}
