package br.com.ninb.moper.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name="LAYOUT")
public class Layout implements Serializable {

	private static final long serialVersionUID = -2495404439149186612L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAYOUT_ID")
	private int layoutId;
	
	@Column(name = "INDEX", nullable = false , unique = true)
	private  int index;
	
	@Column(name = "BEGIN", nullable = false)
	private int begin;
	
	@Column(name = "END", nullable = false)
	private int end;
	
	@Column(name = "LENGHT", nullable = false)
	private int lenght;
	
	
	@Column(name = "TYPECOL", nullable = false)
	private TypeColEnum typeCol;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 80)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "ROWTYPE_ID", nullable = false)
	private RowType rowType;
	
	@ManyToOne
	@JoinColumn(name = "LAYOUTTYPE_ID", nullable = false)
	private LayoutType layoutType;
	
	@ManyToOne
	@JoinColumn(name = "LAYOUTVERSION_ID", nullable = false)
	private LayoutVersion layoutVersion;

	
	public Layout(){
		
	}
	

	public long getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}
	
	public long getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public long getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	
	public TypeColEnum getTypeCol() {
		return typeCol;
	}

	public void setTypeCol(TypeColEnum typeCol) {
		this.typeCol = typeCol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RowType getRowType() {
		return rowType;
	}

	public void setRowType(RowType rowType) {
		this.rowType = rowType;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (layoutId ^ (layoutId >>> 32));
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
		Layout other = (Layout) obj;
		if (layoutId != other.layoutId)
			return false;
		return true;
	}


	
	
	
}
