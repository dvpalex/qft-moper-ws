package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="LAYOUT")
public class Layout implements Serializable {

	private static final long serialVersionUID = -2495404439149186612L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAYOUT_ID")
	private Long layoutId;
	
	@Column(name = "INDEXFIELD", nullable = false)
	private  int indexField;
	
	@Column(name = "BEGINFIELD", nullable = false)
	private int beginField;
	
	@Column(name = "ENDFIELD", nullable = false)
	private int endField;
	
	@Column(name = "LENGHTFIELD", nullable = false)
	private int lenghtField;
	
	@Column(name = "TYPECOL", nullable = false)
	@Enumerated (EnumType.STRING)
	private TypeColEnum typeCol;
	
	@Column(name = "DESCR", nullable = false, length = 80)
	private String descr;
	
	@Column(name = "COLNAME", nullable = false, length = 80)
	private String colName;
	
	@Column(name = "ATIVO", nullable = false, length = 80)
	private Integer ativo;
		
	@ManyToOne
	@JoinColumn(name = "ROWTYPE_ID", nullable = false)
	private RowType rowType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "LAYOUTVERSION_ID", nullable = false)
	private LayoutVersion layoutVersion;
	
	@ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
	@JoinTable(name="LayoutVersionLayout", joinColumns ={ @JoinColumn (name="LAYOUT_ID") }, 
			inverseJoinColumns = {
			 @JoinColumn(name = "LAYOUTVERSION_ID")})
	private List<LayoutVersion> layoutVersions = new ArrayList<LayoutVersion>();

	public Long getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(Long layoutId) {
		this.layoutId = layoutId;
	}

	public int getIndexField() {
		return indexField;
	}

	public void setIndexField(int indexField) {
		this.indexField = indexField;
	}

	public int getBeginField() {
		return beginField;
	}

	public void setBeginField(int beginField) {
		this.beginField = beginField;
	}

	public int getEndField() {
		return endField;
	}

	public void setEndField(int endField) {
		this.endField = endField;
	}

	public int getLenghtField() {
		return lenghtField;
	}

	public void setLenghtField(int lenghtField) {
		this.lenghtField = lenghtField;
	}

	public TypeColEnum getTypeCol() {
		return typeCol;
	}

	public void setTypeCol(TypeColEnum typeCol) {
		this.typeCol = typeCol;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
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

	public List<LayoutVersion> getLayoutVersions() {
		return layoutVersions;
	}

	public void setLayoutVersions(List<LayoutVersion> layoutVersions) {
		this.layoutVersions = layoutVersions;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (layoutId ^ (layoutId >>> 32));
		return result;
	}*/

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

	public void count()
	{
		lenghtField = endField - beginField;
	}
}
