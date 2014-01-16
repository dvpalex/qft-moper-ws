package br.com.ninb.moper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="OUTPUTROWREGISTER")
public class OutputRowRegister
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="OUTPUTROWREGISTER_ID")
	private Long outputRowRegisterId;
	
	@Column(name="CONTENT", nullable=false)
	private String content;
	
	@Column(name="ROW_INDEX", nullable=false)
	private Long rowIndex;
	
	@ManyToOne
	@JoinColumn(name = "ROWTYPE_ID", nullable = false)
	private RowType rowType;
	
	@ManyToOne
	@JoinColumn(name = "OUTPUTREGISTER_ID", nullable = false)
	private OutputRegister outputRegister;

	
	
	public long getOutputRowRegisterId() {
		return outputRowRegisterId;
	}

	public void setOutputRowRegisterId(long outputRowRegisterId) {
		this.outputRowRegisterId = outputRowRegisterId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(long rowIndex) {
		this.rowIndex = rowIndex;
	}

	public RowType getRowType() {
		return rowType;
	}

	public void setRowType(RowType rowType) {
		this.rowType = rowType;
	}

	public OutputRegister getOutputRegister() {
		return outputRegister;
	}

	public void setOutputRegister(OutputRegister outputRegister) {
		this.outputRegister = outputRegister;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (outputRowRegisterId ^ (outputRowRegisterId >>> 32));
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
		OutputRowRegister other = (OutputRowRegister) obj;
		if (outputRowRegisterId != other.outputRowRegisterId)
			return false;
		return true;
	}
}
