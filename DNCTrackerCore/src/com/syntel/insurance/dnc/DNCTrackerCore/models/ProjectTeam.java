package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PROJECT_TEAM database table.
 * 
 */
@Entity
@Table(name="PROJECT_TEAM")
@NamedQuery(name="ProjectTeam.findAll", query="SELECT p FROM ProjectTeam p")
public class ProjectTeam implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjectTeamPK id;

	@Column(name="ASSIGNMENT_HISTORY")
	private String assignmentHistory;

	@Column(name = "BAND")
	private String band;

	@Column(name="BENCH_LOCATION_CITY")
	private String benchLocationCity;

	@Column(name="BENCH_LOCATION_CODE")
	private String benchLocationCode;

	@Temporal(TemporalType.DATE)
	@Column(name="BILLING_DATE")
	private Date billingDate;

	@Column(name="BUFFER_BENCH_REASON")
	private String bufferBenchReason;

	@Column(name="BUFFER_TYPE")
	private String bufferType;

	@Column(name = "CITY")
	private String city;

	@Column(name="COMPANY_CODE")
	private String companyCode;

	@Column(name="COMPANY_DESCRIPTION")
	private String companyDescription;

	@Column(name="CONTACT_NAME")
	private String contactName;

	@Column(name="CONTACT_SEQUENCE_NUMBER")
	private String contactSequenceNumber;

	@Column(name="CUSTOMER_REFERENCE")
	private String customerReference;

	@Column(name="CUSTOMER_ROLE")
	private String customerRole;

	@Column(name="DEVELOPMENT_UNIT")
	private String developmentUnit;

	@Column(name="EMPLOYEE_NAME")
	private String employeeName;

	@Column(name = "GRADE")
	private String grade;

	@Column(name = "LOB")
	private String lob;

	@Column(name="LOCATION_TYPE")
	private String locationType;

	@Column(name="PERMISSIBLE_ACTIVITY_CODE")
	private String permissibleActivityCode;

	@Column(name="PERMISSIBLE_ACTIVITY_DESCRIPTION")
	private String permissibleActivityDescription;

	@Column(name="PRIMARY_FLAG")
	private boolean primaryFlag;

	@Column(name="PROJECT_ROLE")
	private String projectRole;

	@Column(name="RELEASE_REASON")
	private String releaseReason;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="SUPERVISOR_ID")
	private int supervisorId;
	
	@Column(name="SUPERVISOR_NAME")
	private String supervisorName;
	
	@Column(name="UNIT_DESCRIPTION")
	private String unitDescription;

	@Column(name="VISA_DESCRIPTION")
	private String visaDescription;

	@Column(name="VISA_TYPE")
	private String visaType;

	@Column(name="WORK_LOCATION")
	private int workLocation;

	public ProjectTeam() {
	}

	public ProjectTeamPK getId() {
		return this.id;
	}

	public void setId(ProjectTeamPK id) {
		this.id = id;
	}

	public String getAssignmentHistory() {
		return this.assignmentHistory;
	}

	public void setAssignmentHistory(String assignmentHistory) {
		this.assignmentHistory = assignmentHistory;
	}

	public String getBand() {
		return this.band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getBenchLocationCity() {
		return this.benchLocationCity;
	}

	public void setBenchLocationCity(String benchLocationCity) {
		this.benchLocationCity = benchLocationCity;
	}

	public String getBenchLocationCode() {
		return this.benchLocationCode;
	}

	public void setBenchLocationCode(String benchLocationCode) {
		this.benchLocationCode = benchLocationCode;
	}

	public Date getBillingDate() {
		return this.billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public String getBufferBenchReason() {
		return this.bufferBenchReason;
	}

	public void setBufferBenchReason(String bufferBenchReason) {
		this.bufferBenchReason = bufferBenchReason;
	}

	public String getBufferType() {
		return this.bufferType;
	}

	public void setBufferType(String bufferType) {
		this.bufferType = bufferType;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyDescription() {
		return this.companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactSequenceNumber() {
		return this.contactSequenceNumber;
	}

	public void setContactSequenceNumber(String contactSequenceNumber) {
		this.contactSequenceNumber = contactSequenceNumber;
	}

	public String getCustomerReference() {
		return this.customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public String getCustomerRole() {
		return this.customerRole;
	}

	public void setCustomerRole(String cellValue) {
		this.customerRole = cellValue;
	}

	public String getDevelopmentUnit() {
		return this.developmentUnit;
	}

	public void setDevelopmentUnit(String developmentUnit) {
		this.developmentUnit = developmentUnit;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLob() {
		return this.lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getPermissibleActivityCode() {
		return this.permissibleActivityCode;
	}

	public void setPermissibleActivityCode(String permissibleActivityCode) {
		this.permissibleActivityCode = permissibleActivityCode;
	}

	public String getPermissibleActivityDescription() {
		return this.permissibleActivityDescription;
	}

	public void setPermissibleActivityDescription(String permissibleActivityDescription) {
		this.permissibleActivityDescription = permissibleActivityDescription;
	}

	public boolean isPrimaryFlag() {
		return this.primaryFlag;
	}

	public void setPrimaryFlag(boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public String getProjectRole() {
		return this.projectRole;
	}

	public void setProjectRole(String projectRole) {
		this.projectRole = projectRole;
	}

	public String getReleaseReason() {
		return this.releaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getUnitDescription() {
		return this.unitDescription;
	}

	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public String getVisaDescription() {
		return this.visaDescription;
	}

	public void setVisaDescription(String visaDescription) {
		this.visaDescription = visaDescription;
	}

	public String getVisaType() {
		return this.visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public int getWorkLocation() {
		return this.workLocation;
	}

	public void setWorkLocation(int workLocation) {
		this.workLocation = workLocation;
	}

}