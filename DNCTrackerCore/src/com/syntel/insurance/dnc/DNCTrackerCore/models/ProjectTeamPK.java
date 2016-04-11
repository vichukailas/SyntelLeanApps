package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PROJECT_TEAM database table.
 * 
 */
@Embeddable
public class ProjectTeamPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EMPLOYEE_ID")
	private int employeeId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private java.util.Date endDate;

	@Column(name="PROJECT_ID")
	private int projectId;

	public ProjectTeamPK() {
	}
	public int getEmployeeId() {
		return this.employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	public int getProjectId() {
		return this.projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProjectTeamPK)) {
			return false;
		}
		ProjectTeamPK castOther = (ProjectTeamPK)other;
		return 
			(this.employeeId == castOther.employeeId)
			&& this.endDate.equals(castOther.endDate)
			&& (this.projectId == castOther.projectId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.employeeId;
		hash = hash * prime + this.endDate.hashCode();
		hash = hash * prime + this.projectId;
		
		return hash;
	}
}