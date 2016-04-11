package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PREMISSIBLE_BUFFER database table.
 * 
 */
@Entity
@Table(name="PREMISSIBLE_BUFFER")
@NamedQuery(name="PremissibleBuffer.findAll", query="SELECT p FROM PremissibleBuffer p")
public class PremissibleBuffer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROJECT_ID")
	private int projectId;

	@Column(name="PERCENTAGE_PERMISSIBLE")
	private BigDecimal percentagePermissible;

	public PremissibleBuffer() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getPercentagePermissible() {
		return this.percentagePermissible;
	}

	public void setPercentagePermissible(BigDecimal percentagePermissible) {
		this.percentagePermissible = percentagePermissible;
	}

}