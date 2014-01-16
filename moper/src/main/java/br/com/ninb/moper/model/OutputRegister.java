package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Entity
@Table(name="OUTPUTREGISTER")
@Configurable
@Component
public class OutputRegister implements Serializable {

	private static final long serialVersionUID = 3320157644115634934L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OUTPUTREGISTER_ID")
	private Long outputRegisterId;
	
	@Column(name = "DETAILCOUNT", nullable = false)
	private Long detailCount;
	
	@Column(name = "GENERATEDATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date generateDate;
	
	@ManyToOne
	@JoinColumn(name = "LAYOUTTYPE_ID", nullable = false)
	private LayoutType layoutType;
	
	@ManyToOne
	@JoinColumn(name = "LAYOUTVERSION_ID", nullable = false)
	private LayoutVersion layoutVersion;
		
	@Column(name = "STATUS", nullable = false)
	private int status;
	
	@Column(name = "EVENT", nullable = true, length = 80)
	private String event;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "outputRegister")
    @LazyCollection(LazyCollectionOption.TRUE)
	private List<OutputRowRegister> rows;
	
	@Column(name = "FILENAME", nullable = false)
	private String fileName;

	@Column(name = "NUMSEQ", nullable = false)
	private Long numSeq;
	
	public OutputRegister(){
		
	}	

	public Long getOutputRegisterId() {
		return outputRegisterId;
	}


	public void setOutputRegisterId(Long outputRegisterId) {
		this.outputRegisterId = outputRegisterId;
	}



	public Long getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Long detailCount) {
		this.detailCount = detailCount;
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



	public LayoutVersion getLayoutVersion() {
		return layoutVersion;
	}



	public void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getEvent() {
		return event;
	}



	public void setEvent(String event) {
		this.event = event;
	}



	public List<OutputRowRegister> getRows() {
		return rows;
	}



	public void setRows(List<OutputRowRegister> rows) {
		this.rows = rows;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public Long getNumSeq() {
		return numSeq;
	}



	public void setNumSeq(Long numSeq) {
		this.numSeq = numSeq;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (outputRegisterId ^ (outputRegisterId >>> 32));
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
		OutputRegister other = (OutputRegister) obj;
		if (outputRegisterId != other.outputRegisterId)
			return false;
		return true;
	}
	
	

	
	
	
	
	
}
