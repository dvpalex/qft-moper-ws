package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
	
@Entity
@Table(name="LAYOUTVERSION")
public class LayoutVersion implements Serializable{

	
	private static final long serialVersionUID = 6404848335681400328L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAYOUTVERSION_ID")
	private long layoutVersionId;
	
	@Column(name = "GENERATEDATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date generateDate;
	
	@Column(name = "VERSION", nullable = false)
	private long version;
	
	@Column(name = "DESCRIPTION", length = 80)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "LAYOUTTYPE_ID", nullable = false)
	private LayoutType layoutType;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "layoutVersion")
    @LazyCollection(LazyCollectionOption.TRUE)
	private List<OutputRegister> outputRegisters = new ArrayList<OutputRegister>();
	
	@ManyToMany(mappedBy = "layoutVersions")
	private List<Layout> layouts = new ArrayList<Layout>();
	
	
	public LayoutVersion(){
		
	}
	
	public List<OutputRegister> getOutputRegisters() {
		return outputRegisters;
	}

	public void setOutputRegisters(List<OutputRegister> outputRegisters) {
		this.outputRegisters = outputRegisters;
	}


	public Date getGenerateDate() {
		return generateDate;
	}


	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}

	public LayoutType getLayoutType() {
		return layoutType;
	}


	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public List<Layout> getLayouts() {
		return layouts;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	public long getLayoutVersionId() {
		return layoutVersionId;
	}


	public void setLayoutVersionId(long layoutVersionId) {
		this.layoutVersionId = layoutVersionId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (layoutVersionId ^ (layoutVersionId >>> 32));
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
		LayoutVersion other = (LayoutVersion) obj;
		if (layoutVersionId != other.layoutVersionId)
			return false;
		return true;
	}
	
}
