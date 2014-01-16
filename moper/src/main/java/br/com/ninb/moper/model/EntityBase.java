package br.com.ninb.moper.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class EntityBase implements Serializable {

	private static final long serialVersionUID = 7108760821263160996L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name="DTINC", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dtInc;
	
	@Column(name="USERID")
	private Long userId;
	
	
	
}
