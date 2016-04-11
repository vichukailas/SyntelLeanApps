package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIMESHEET database table.
 * 
 */
@Entity
@NamedQuery(name="Timesheet.findAll", query="SELECT t FROM Timesheet t")
public class Timesheet implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TIMSHEET_ID")
	private int timsheetId;

	private float bereavement;

	@Column(name="BILLABLE_HOURS")
	private float billableHours;

	@Column(name="BUFFER_FLAG")
	private boolean bufferFlag;

	@Column(name="CLIENT_HOLIDAY")
	private float clientHoliday;

	@Column(name="CLIENT_TIMESHEET")
	private boolean clientTimesheet;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CUSTOMER_ID")
	private int customerId;

	@Column(name="CUSTOMER_NAME")
	private String customerName;

	@Column(name="EMPLOYEE_ID")
	private int employeeId;

	@Column(name="EMPLOYEE_NAME")
	private String employeeName;

	@Column(name="HOUR_DIFFERENCE")
	private float hourDifference;

	private float jury;

	@Column(name="LOCATION_TYPE")
	private String locationType;

	private float mto;

	@Column(name="PAY_PERIOD")
	private String payPeriod;

	@Column(name="PRIMARY_FLAG")
	private boolean primaryFlag;

	@Column(name="PROJECT_ID")
	private int projectId;

	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="PROJECT_OWNING_COMPANY")
	private String projectOwningCompany;

	@Column(name="PROJECT_TYPE")
	private String projectType;

	@Column(name="REPORTING_MANAGER")
	private String reportingManager;

	@Column(name="STANDARD_HOURS")
	private float standardHours;

	private String status;

	@Column(name="TOTAL_HOURS")
	private float totalHours;

	private float training;

	@Column(name="UNBILLED_WORKHOURS")
	private float unbilledWorkhours;

	@Column(name="WEEKLY_OFF")
	private float weeklyOff;

	@Column(name="WORK_HOURS")
	private float workHours;

	@Column(name="WORK_PROJECT_CITY")
	private String workProjectCity;

	@Column(name="WORK_PROJECT_COUNTRY")
	private String workProjectCountry;

	@Column(name="WORK_PROJECT_STATE")
	private String workProjectState;

	public Timesheet() {
	}

	public int getTimsheetId() {
		return this.timsheetId;
	}

	public void setTimsheetId(int timsheetId) {
		this.timsheetId = timsheetId;
	}

	public float getBereavement() {
		return this.bereavement;
	}

	public void setBereavement(float bereavement) {
		this.bereavement = bereavement;
	}

	public float getBillableHours() {
		return this.billableHours;
	}

	public void setBillableHours(float billableHours) {
		this.billableHours = billableHours;
	}

	public boolean isBufferFlag() {
		return this.bufferFlag;
	}

	public void setBufferFlag(boolean bufferFlag) {
		this.bufferFlag = bufferFlag;
	}

	public float getClientHoliday() {
		return this.clientHoliday;
	}

	public void setClientHoliday(float clientHoliday) {
		this.clientHoliday = clientHoliday;
	}

	public boolean isClientTimesheet() {
		return this.clientTimesheet;
	}

	public void setClientTimesheet(boolean clientTimesheet) {
		this.clientTimesheet = clientTimesheet;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public float getHourDifference() {
		return this.hourDifference;
	}

	public void setHourDifference(float hourDifference) {
		this.hourDifference = hourDifference;
	}

	public float getJury() {
		return this.jury;
	}

	public void setJury(float jury) {
		this.jury = jury;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public float getMto() {
		return this.mto;
	}

	public void setMto(float mto) {
		this.mto = mto;
	}

	public String getPayPeriod() {
		return this.payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public boolean isPrimaryFlag() {
		return this.primaryFlag;
	}

	public void setPrimaryFlag(boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
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

	public String getProjectOwningCompany() {
		return this.projectOwningCompany;
	}

	public void setProjectOwningCompany(String projectOwningCompany) {
		this.projectOwningCompany = projectOwningCompany;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getReportingManager() {
		return this.reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public float getStandardHours() {
		return this.standardHours;
	}

	public void setStandardHours(float standardHours) {
		this.standardHours = standardHours;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getTotalHours() {
		return this.totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public float getTraining() {
		return this.training;
	}

	public void setTraining(float training) {
		this.training = training;
	}

	public float getUnbilledWorkhours() {
		return this.unbilledWorkhours;
	}

	public void setUnbilledWorkhours(float unbilledWorkhours) {
		this.unbilledWorkhours = unbilledWorkhours;
	}

	public float getWeeklyOff() {
		return this.weeklyOff;
	}

	public void setWeeklyOff(float weeklyOff) {
		this.weeklyOff = weeklyOff;
	}

	public float getWorkHours() {
		return this.workHours;
	}

	public void setWorkHours(float workHours) {
		this.workHours = workHours;
	}

	public String getWorkProjectCity() {
		return this.workProjectCity;
	}

	public void setWorkProjectCity(String workProjectCity) {
		this.workProjectCity = workProjectCity;
	}

	public String getWorkProjectCountry() {
		return this.workProjectCountry;
	}

	public void setWorkProjectCountry(String workProjectCountry) {
		this.workProjectCountry = workProjectCountry;
	}

	public String getWorkProjectState() {
		return this.workProjectState;
	}

	public void setWorkProjectState(String workProjectState) {
		this.workProjectState = workProjectState;
	}

}