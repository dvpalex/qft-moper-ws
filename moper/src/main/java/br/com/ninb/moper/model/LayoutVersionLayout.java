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

@Entity
@Table(name="LAYOUTVERSIONLAYOUT")
public class LayoutVersionLayout implements Serializable{

	
	private static final long serialVersionUID = 4004816298307484475L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAYOUTVERSIONLAYOUT_ID")
	private Long layoutVersionLayoutId;
	
	@ManyToOne
	@JoinColumn(name = "LAYOUT_ID", nullable = false)
	private Layout layout;
	
	@ManyToOne
	@JoinColumn(name = "LAYOUTVERSION_ID", nullable = false)
	private LayoutVersion layoutVersion;
	
	
	public Long getLayoutVersionLayoutId() {
		return layoutVersionLayoutId;
	}
	public void setLayoutVersionLayoutId(Long layoutVersionLayoutId) {
		this.layoutVersionLayoutId = layoutVersionLayoutId;
	}
	
		
	public Layout getLayout() {
		return layout;
	}
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	public LayoutVersion getLayoutVersion() {
		return layoutVersion;
	}
	public void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((layoutVersionLayoutId == null) ? 0 : layoutVersionLayoutId
						.hashCode());
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
		LayoutVersionLayout other = (LayoutVersionLayout) obj;
		if (layoutVersionLayoutId == null) {
			if (other.layoutVersionLayoutId != null)
				return false;
		} else if (!layoutVersionLayoutId.equals(other.layoutVersionLayoutId))
			return false;
		return true;
	}
	
	
	

}
