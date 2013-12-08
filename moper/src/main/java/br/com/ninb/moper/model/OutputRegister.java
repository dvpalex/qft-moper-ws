package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OUTPUTREGISTER")
public class OutputRegister implements Serializable {

	private static final long serialVersionUID = 3320157644115634934L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OUTPUTREGISTER_ID")
	private long outputRegisterId;
	
	@Column(name = "DETAILCOUNT", nullable = false)
	private long detailCount;
	
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
	private boolean status;
	
	@Column(name = "EVENT", nullable = true, length = 80)
	private String event;
	
	public OutputRegister(){
		
	}
	
	
	public long getOutputRegisterId() {
		return outputRegisterId;
	}

	public void setOutputRegisterId(long outputRegisterId) {
		this.outputRegisterId = outputRegisterId;
	}


	public long getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(long detailCount) {
		this.detailCount = detailCount;
	}
	
	public Date getGenerateDate() {
		return generateDate;
	}


	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}


	public LayoutVersion getLayoutVersion() {
		return layoutVersion;
	}


	public void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}


	public LayoutType getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(LayoutType layoutType) {
		this.layoutType = layoutType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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
