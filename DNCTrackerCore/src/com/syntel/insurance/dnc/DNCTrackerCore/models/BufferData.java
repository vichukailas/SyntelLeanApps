package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BUFFER_DATA database table.
 * 
 */
@Entity
@Table(name="BUFFER_DATA")
@NamedQuery(name="BufferData.findAll", query="SELECT b FROM BufferData b")
public class BufferData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_ID")
	private int employeeId;

	@Column(name="APR_STAFFING")
	private float aprStaffing;

	@Column(name="APR_YTD_STAFFING")
	private float aprYtdStaffing;

	@Temporal(TemporalType.DATE)
	@Column(name="ASSIGNMENT_END_DATE")
	private Date assignmentEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name="ASSIGNMENT_START_DATE")
	private Date assignmentStartDate;

	@Column(name="AUG_STAFFING")
	private float augStaffing;

	@Column(name="AUG_YTD_STAFFING")
	private float augYtdStaffing;

	@Column(name="BAND_GRADE")
	private String bandGrade;

	private String customer;

	@Column(name="DEC_STAFFING")
	private float decStaffing;

	@Column(name="DEC_YTD_STAFFING")
	private float decYtdStaffing;

	private String designation;

	@Column(name="EMPLOYEE_NAME")
	private String employeeName;

	@Column(name="FEB_STAFFING")
	private float febStaffing;

	@Column(name="FEB_YTD_STAFFING")
	private float febYtdStaffing;

	@Column(name="JAN_STAFFING")
	private float janStaffing;

	@Column(name="JAN_YTD_STAFFING")
	private float janYtdStaffing;

	@Column(name="JUL_STAFFING")
	private float julStaffing;

	@Column(name="JUL_YTD_STAFFING")
	private float julYtdStaffing;

	@Column(name="JUN_STAFFING")
	private float junStaffing;

	@Column(name="JUN_YTD_STAFFING")
	private float junYtdStaffing;

	@Column(name="LOCATION_TYPE")
	private String locationType;

	@Column(name="MAR_STAFFING")
	private float marStaffing;

	@Column(name="MAR_YTD_STAFFING")
	private float marYtdStaffing;

	@Column(name="MAY_STAFFING")
	private float mayStaffing;

	@Column(name="MAY_YTD_STAFFING")
	private float mayYtdStaffing;

	@Column(name="MSO_OR_BU")
	private String msoOrBu;

	@Column(name="NOV_STAFFING")
	private float novStaffing;

	@Column(name="NOV_YTD_STAFFING")
	private float novYtdStaffing;

	@Column(name="OCT_STAFFING")
	private float octStaffing;

	@Column(name="OCT_YTD_STAFFING")
	private float octYtdStaffing;

	@Temporal(TemporalType.DATE)
	@Column(name="PROJECT_END_DATE")
	private Date projectEndDate;

	@Column(name="PROJECT_ID")
	private int projectId;

	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="PROJECT_TYPE")
	private String projectType;

	@Column(name="SEP_STAFFING")
	private float sepStaffing;

	@Column(name="SEP_YTD_STAFFING")
	private float sepYtdStaffing;

	private String status;

	@Column(name="STATUS_PER_END_DATE")
	private String statusPerEndDate;

	@Column(name="VERTICAL_GROUP")
	private String verticalGroup;

	public BufferData() {
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public float getAprStaffing() {
		return this.aprStaffing;
	}

	public void setAprStaffing(float aprStaffing) {
		this.aprStaffing = aprStaffing;
	}

	public float getAprYtdStaffing() {
		return this.aprYtdStaffing;
	}

	public void setAprYtdStaffing(float aprYtdStaffing) {
		this.aprYtdStaffing = aprYtdStaffing;
	}

	public Date getAssignmentEndDate() {
		return this.assignmentEndDate;
	}

	public void setAssignmentEndDate(Date assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}

	public Date getAssignmentStartDate() {
		return this.assignmentStartDate;
	}

	public void setAssignmentStartDate(Date assignmentStartDate) {
		this.assignmentStartDate = assignmentStartDate;
	}

	public float getAugStaffing() {
		return this.augStaffing;
	}

	public void setAugStaffing(float augStaffing) {
		this.augStaffing = augStaffing;
	}

	public float getAugYtdStaffing() {
		return this.augYtdStaffing;
	}

	public void setAugYtdStaffing(float augYtdStaffing) {
		this.augYtdStaffing = augYtdStaffing;
	}

	public String getBandGrade() {
		return this.bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public float getDecStaffing() {
		return this.decStaffing;
	}

	public void setDecStaffing(float decStaffing) {
		this.decStaffing = decStaffing;
	}

	public float getDecYtdStaffing() {
		return this.decYtdStaffing;
	}

	public void setDecYtdStaffing(float decYtdStaffing) {
		this.decYtdStaffing = decYtdStaffing;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public float getFebStaffing() {
		return this.febStaffing;
	}

	public void setFebStaffing(float febStaffing) {
		this.febStaffing = febStaffing;
	}

	public float getFebYtdStaffing() {
		return this.febYtdStaffing;
	}

	public void setFebYtdStaffing(float febYtdStaffing) {
		this.febYtdStaffing = febYtdStaffing;
	}

	public float getJanStaffing() {
		return this.janStaffing;
	}

	public void setJanStaffing(float janStaffing) {
		this.janStaffing = janStaffing;
	}

	public float getJanYtdStaffing() {
		return this.janYtdStaffing;
	}

	public void setJanYtdStaffing(float janYtdStaffing) {
		this.janYtdStaffing = janYtdStaffing;
	}

	public float getJulStaffing() {
		return this.julStaffing;
	}

	public void setJulStaffing(float julStaffing) {
		this.julStaffing = julStaffing;
	}

	public float getJulYtdStaffing() {
		return this.julYtdStaffing;
	}

	public void setJulYtdStaffing(float julYtdStaffing) {
		this.julYtdStaffing = julYtdStaffing;
	}

	public float getJunStaffing() {
		return this.junStaffing;
	}

	public void setJunStaffing(float junStaffing) {
		this.junStaffing = junStaffing;
	}

	public float getJunYtdStaffing() {
		return this.junYtdStaffing;
	}

	public void setJunYtdStaffing(float junYtdStaffing) {
		this.junYtdStaffing = junYtdStaffing;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public float getMarStaffing() {
		return this.marStaffing;
	}

	public void setMarStaffing(float marStaffing) {
		this.marStaffing = marStaffing;
	}

	public float getMarYtdStaffing() {
		return this.marYtdStaffing;
	}

	public void setMarYtdStaffing(float marYtdStaffing) {
		this.marYtdStaffing = marYtdStaffing;
	}

	public float getMayStaffing() {
		return this.mayStaffing;
	}

	public void setMayStaffing(float mayStaffing) {
		this.mayStaffing = mayStaffing;
	}

	public float getMayYtdStaffing() {
		return this.mayYtdStaffing;
	}

	public void setMayYtdStaffing(float mayYtdStaffing) {
		this.mayYtdStaffing = mayYtdStaffing;
	}

	public String getMsoOrBu() {
		return this.msoOrBu;
	}

	public void setMsoOrBu(String msoOrBu) {
		this.msoOrBu = msoOrBu;
	}

	public float getNovStaffing() {
		return this.novStaffing;
	}

	public void setNovStaffing(float novStaffing) {
		this.novStaffing = novStaffing;
	}

	public float getNovYtdStaffing() {
		return this.novYtdStaffing;
	}

	public void setNovYtdStaffing(float novYtdStaffing) {
		this.novYtdStaffing = novYtdStaffing;
	}

	public float getOctStaffing() {
		return this.octStaffing;
	}

	public void setOctStaffing(float octStaffing) {
		this.octStaffing = octStaffing;
	}

	public float getOctYtdStaffing() {
		return this.octYtdStaffing;
	}

	public void setOctYtdStaffing(float octYtdStaffing) {
		this.octYtdStaffing = octYtdStaffing;
	}

	public Date getProjectEndDate() {
		return this.projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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

	public float getSepStaffing() {
		return this.sepStaffing;
	}

	public void setSepStaffing(float sepStaffing) {
		this.sepStaffing = sepStaffing;
	}

	public float getSepYtdStaffing() {
		return this.sepYtdStaffing;
	}

	public void setSepYtdStaffing(float sepYtdStaffing) {
		this.sepYtdStaffing = sepYtdStaffing;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusPerEndDate() {
		return this.statusPerEndDate;
	}

	public void setStatusPerEndDate(String statusPerEndDate) {
		this.statusPerEndDate = statusPerEndDate;
	}

	public String getVerticalGroup() {
		return this.verticalGroup;
	}

	public void setVerticalGroup(String verticalGroup) {
		this.verticalGroup = verticalGroup;
	}

}