package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OFFSHORE_BILLABLE_HOURS database table.
 * 
 */
@Entity
@Table(name="OFFSHORE_BILLABLE_HOURS")
@NamedQuery(name="OffshoreBillableHour.findAll", query="SELECT o FROM OffshoreBillableHour o")
public class OffshoreBillableHour implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROJECT_ID")
	private int projectId;

	@Column(name="BILLABLE_HOURS")
	private double billableHours;

	@Column(name="CUSTOMER_ID")
	private int customerId;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="PROJECT_TYPE")
	private String projectType;

	public OffshoreBillableHour() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public double getBillableHours() {
		return this.billableHours;
	}

	public void setBillableHours(double billableHours) {
		this.billableHours = billableHours;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

}