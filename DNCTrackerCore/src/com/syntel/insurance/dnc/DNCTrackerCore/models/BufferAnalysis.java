package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class BufferAnalysis implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String verticalGroup;
	private String msoOrBu;
	private String customer;
	private int projectId;
	private String projectName;
	private String projectType;
	private double offshoreBillableHours;
	private double bufferHours;
	private BigDecimal percentPermissible;
	private double excessBuffer;
	private double percentBuffer;
	private double costOfNc;
	private String dncCategory;

	public BufferAnalysis() {

	}

	public BufferAnalysis(String verticalGroup, String msoOrBu, String customer, int projectId, String projectName,
			String projectType, double offshoreBillableHours, double bufferHours, BigDecimal percentPermissible,
			double excessBuffer, double percentBuffer, double costOfNc, String dncCategory) {
		this.verticalGroup = verticalGroup;
		this.msoOrBu = msoOrBu;
		this.customer = customer;
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectType = projectType;
		this.offshoreBillableHours = offshoreBillableHours;
		this.bufferHours = bufferHours;
		this.percentPermissible = percentPermissible;
		this.excessBuffer = excessBuffer;
		this.percentBuffer = percentBuffer;
		this.costOfNc = costOfNc;
		this.dncCategory = dncCategory;
	}

	public BufferAnalysis(String verticalGroup, String msoOrBu, String customer, int projectId, String projectName,
			String projectType, double offshoreBillableHours, double bufferHours, BigDecimal percentPermissible) {
		this.verticalGroup = verticalGroup;
		this.msoOrBu = msoOrBu;
		this.customer = customer;
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectType = projectType;
		this.offshoreBillableHours = offshoreBillableHours;
		this.bufferHours = bufferHours;
		this.percentPermissible = percentPermissible;
	}

	public String getVerticalGroup() {
		return verticalGroup;
	}

	public void setVerticalGroup(String verticalGroup) {
		this.verticalGroup = verticalGroup;
	}

	public String getMsoOrBu() {
		return msoOrBu;
	}

	public void setMsoOrBu(String msoOrBu) {
		this.msoOrBu = msoOrBu;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public double getOffshoreBillableHours() {
		return offshoreBillableHours;
	}

	public void setOffshoreBillableHours(double offshoreBillableHours) {
		this.offshoreBillableHours = offshoreBillableHours;
	}

	public double getBufferHours() {
		return bufferHours;
	}

	public void setBufferHours(double bufferHours) {
		this.bufferHours = bufferHours;
	}

	public BigDecimal getPercentPermissible() {
		return percentPermissible;
	}

	public void setPercentPermissible(BigDecimal percentPermissible) {
		this.percentPermissible = percentPermissible;
	}

	public double getExcessBuffer() {
		return excessBuffer;
	}

	public void setExcessBuffer(double excessBuffer) {
		this.excessBuffer = excessBuffer;
	}

	public double getPercentBuffer() {
		return percentBuffer;
	}

	public void setPercentBuffer(double percentBuffer) {
		this.percentBuffer = percentBuffer;
	}

	public double getCostOfNc() {
		return costOfNc;
	}

	public void setCostOfNc(double costOfNc) {
		this.costOfNc = costOfNc;
	}

	public String getDncCategory() {
		return dncCategory;
	}

	public void setDncCategory(String dncCategory) {
		this.dncCategory = dncCategory;
	}

}
