package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the UNBILLED_LEAKAGE_PID database table.
 * 
 */
@Embeddable
public class UnbilledLeakagePidPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "EMP_ID")
	private int empId;

	@Column(name = "PROJECT_ID")
	private int projectId;

	public UnbilledLeakagePidPK() {
	}

	public UnbilledLeakagePidPK(int empId, int projectId) {
		this.empId = empId;
		this.projectId = projectId;
	}

	public int getEmpId() {
		return this.empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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
		if (!(other instanceof UnbilledLeakagePidPK)) {
			return false;
		}
		UnbilledLeakagePidPK castOther = (UnbilledLeakagePidPK) other;
		return (this.empId == castOther.empId) && (this.projectId == castOther.projectId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.empId;
		hash = hash * prime + this.projectId;

		return hash;
	}
}