package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HEAD_COUNT_REPORT database table.
 * 
 */
@Entity
@Table(name="HEAD_COUNT_REPORT")
@NamedQuery(name="HeadCountReport.findAll", query="SELECT h FROM HeadCountReport h")
public class HeadCountReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EMPLOYEE_ID")
	private int employeeId;

	@Column(name="AM_PM_ID")
	private int amPmId;

	@Column(name="AM_PM_NAME")
	private String amPmName;

	@Temporal(TemporalType.DATE)
	@Column(name="ASSIGNMENT_END_DATE")
	private Date assignmentEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name="ASSIGNMENT_START_DATE")
	private Date assignmentStartDate;

	@Column(name="BAND_GRADE")
	private String bandGrade;

	@Column(name = "BILLABILITY")
	private String billability;

	@Temporal(TemporalType.DATE)
	@Column(name="BILLING_START_DATE")
	private Date billingStartDate;

	@Column(name="BUSINESS_TYPE")
	private String businessType;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="COST_CENTER")
	private String costCenter;

	private String customer;

	@Column(name="DD_ID")
	private int ddId;

	@Column(name="DD_NAME")
	private String ddName;

	@Column(name="DELIVERY_LOCATION")
	private String deliveryLocation;

	private String designation;

	@Column(name="EM_DM_ID")
	private int emDmId;

	@Column(name="EM_DM_NAME")
	private String emDmName;

	@Column(name="EMPLOYEE_COUNTRY_CODE")
	private String employeeCountryCode;

	@Column(name="EMPLOYEE_NAME")
	private String employeeName;

	@Column(name="EMPLOYEE_REGION")
	private String employeeRegion;

	@Column(name = "LOB")
	private String lob;

	@Column(name = "LOCATION")
	private String location;

	@Column(name="LOCATION_TYPE")
	private String locationType;

	@Column(name="MSO_OR_BU")
	private String msoOrBu;

	@Column(name="OFFSHORE_AM_ID")
	private int offshoreAmId;

	@Column(name="OFFSHORE_AM_NAME")
	private String offshoreAmName;

	@Column(name="PROJECT_CATEGORY")
	private String projectCategory;

	@Column(name="PROJECT_COUNTRY_CODE")
	private String projectCountryCode;

	@Column(name="PROJECT_ID")
	private int projectId;

	@Column(name="PROJECT_LOB")
	private String projectLob;

	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="PROJECT_TYPE")
	private String projectType;

	@Column(name="RM_ID")
	private int rmId;

	@Column(name="RM_NAME")
	private String rmName;

	@Column(name="SOW_RECORD")
	private boolean sowRecord;
	
	@Column(name = "STATUS")
	private String status;

	@Column(name="UNIT_NAME")
	private String unitName;
	
	@Column(name = "VERTICAL")
	private String vertical;

	@Column(name="VERTICAL_GROUP")
	private String verticalGroup;

	@Column(name="VERTICAL_HEAD_ID")
	private int verticalHeadId;

	@Column(name="VERTICAL_HEAD_NAME")
	private String verticalHeadName;

	@Temporal(TemporalType.DATE)
	@Column(name="VISA_EXP_DATE")
	private Date visaExpDate;

	@Column(name="VISA_TYPE")
	private String visaType;

	@Column(name="WORK_COUNTRY_CODE")
	private String workCountryCode;

	@Column(name="WORK_LOCATION")
	private String workLocation;

	public HeadCountReport() {
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getAmPmId() {
		return this.amPmId;
	}

	public void setAmPmId(int amPmId) {
		this.amPmId = amPmId;
	}

	public String getAmPmName() {
		return this.amPmName;
	}

	public void setAmPmName(String amPmName) {
		this.amPmName = amPmName;
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

	public String getBandGrade() {
		return this.bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public String getBillability() {
		return this.billability;
	}

	public void setBillability(String billability) {
		this.billability = billability;
	}

	public Date getBillingStartDate() {
		return this.billingStartDate;
	}

	public void setBillingStartDate(Date billingStartDate) {
		this.billingStartDate = billingStartDate;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getDdId() {
		return this.ddId;
	}

	public void setDdId(int ddId) {
		this.ddId = ddId;
	}

	public String getDdName() {
		return this.ddName;
	}

	public void setDdName(String ddName) {
		this.ddName = ddName;
	}

	public String getDeliveryLocation() {
		return this.deliveryLocation;
	}

	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getEmDmId() {
		return this.emDmId;
	}

	public void setEmDmId(int emDmId) {
		this.emDmId = emDmId;
	}

	public String getEmDmName() {
		return this.emDmName;
	}

	public void setEmDmName(String emDmName) {
		this.emDmName = emDmName;
	}

	public String getEmployeeCountryCode() {
		return this.employeeCountryCode;
	}

	public void setEmployeeCountryCode(String employeeCountryCode) {
		this.employeeCountryCode = employeeCountryCode;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeRegion() {
		return this.employeeRegion;
	}

	public void setEmployeeRegion(String employeeRegion) {
		this.employeeRegion = employeeRegion;
	}

	public String getLob() {
		return this.lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getMsoOrBu() {
		return this.msoOrBu;
	}

	public void setMsoOrBu(String msoOrBu) {
		this.msoOrBu = msoOrBu;
	}

	public int getOffshoreAmId() {
		return this.offshoreAmId;
	}

	public void setOffshoreAmId(int offshoreAmId) {
		this.offshoreAmId = offshoreAmId;
	}

	public String getOffshoreAmName() {
		return this.offshoreAmName;
	}

	public void setOffshoreAmName(String offshoreAmName) {
		this.offshoreAmName = offshoreAmName;
	}

	public String getProjectCategory() {
		return this.projectCategory;
	}

	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}

	public String getProjectCountryCode() {
		return this.projectCountryCode;
	}

	public void setProjectCountryCode(String projectCountryCode) {
		this.projectCountryCode = projectCountryCode;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectLob() {
		return this.projectLob;
	}

	public void setProjectLob(String projectLob) {
		this.projectLob = projectLob;
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

	public int getRmId() {
		return this.rmId;
	}

	public void setRmId(int rmId) {
		this.rmId = rmId;
	}

	public String getRmName() {
		return this.rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public boolean getSowRecord() {
		return this.sowRecord;
	}

	public void setSowRecord(boolean sowRecord) {
		this.sowRecord = sowRecord;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getVertical() {
		return this.vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getVerticalGroup() {
		return this.verticalGroup;
	}

	public void setVerticalGroup(String verticalGroup) {
		this.verticalGroup = verticalGroup;
	}

	public int getVerticalHeadId() {
		return this.verticalHeadId;
	}

	public void setVerticalHeadId(int verticalHeadId) {
		this.verticalHeadId = verticalHeadId;
	}

	public String getVerticalHeadName() {
		return this.verticalHeadName;
	}

	public void setVerticalHeadName(String verticalHeadName) {
		this.verticalHeadName = verticalHeadName;
	}

	public Date getVisaExpDate() {
		return this.visaExpDate;
	}

	public void setVisaExpDate(Date visaExpDate) {
		this.visaExpDate = visaExpDate;
	}

	public String getVisaType() {
		return this.visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getWorkCountryCode() {
		return this.workCountryCode;
	}

	public void setWorkCountryCode(String workCountryCode) {
		this.workCountryCode = workCountryCode;
	}

	public String getWorkLocation() {
		return this.workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

}