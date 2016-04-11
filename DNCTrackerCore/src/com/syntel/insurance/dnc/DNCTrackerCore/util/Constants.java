package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.text.SimpleDateFormat;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Constants {

	public static final String PS_FINANCE_BASE_URL = "https://finance.corp.syntel.in";
	public static final String LOGIN_SUCCESS_TITLE_TEXT = "Employee-facing registry content";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	public static final String SQL_UNBILLED_LEAKAGE_PID = " SELECT t.customer_name "
			+ " 		,t.employee_id as EMP_ID" + " 		,t.employee_name " + " 		,t.project_id as PROJECT_ID"
			+ " 		,t.project_name " + " 		,t.location_type " + " 		,t.project_type "
			+ " 		,t.primary_flag " + " 		,SUM(t.standard_hours) AS Standard_hours "
			+ " 		,SUM(t.billable_hours) AS Billable_Hours " + " 		,SUM(t.client_holiday) AS Client_Holiday "
			+ " 		,SUM(t.mto) AS Mto " + ",CASE  " + "	WHEN t.location_type = 'Offshore' "
			+ "		THEN SUM(t.billable_hours) - SUM(t.standard_hours) "
			+ "	ELSE (SUM(t.billable_hours) + SUM(t.client_holiday) + SUM(t.mto)) - SUM(t.standard_hours) "
			+ "	END AS LEAKAGE " + ",CASE  " + "	WHEN t.location_type = 'Offshore' "
			+ "		THEN (SUM(t.billable_hours) - SUM(t.standard_hours))*6.63 "
			+ "	ELSE ((SUM(t.billable_hours) + SUM(t.client_holiday) + SUM(t.mto)) - SUM(t.standard_hours))*38.61 "
			+ "	END AS COST_OF_NC  " + " 	FROM dnc.Timesheet t " + " 	WHERE t.primary_flag = 1 "
			+ " 		AND t.buffer_flag = 0 " + " 	GROUP BY t.customer_name " + " 		,t.employee_id "
			+ " 		,t.employee_name " + " 		,t.project_id " + " 		,t.project_name "
			+ " 		,t.location_type " + " 		,t.project_type " + " 		,t.primary_flag ";

	public static final String SQL_UNBILLED_LEAKAGE_ACCOUNT = " SELECT pid.customer_name " + " 		,pid.location_type"
			+ " 		,pid.project_id " + " 		,pid.project_name " + " 		,pid.project_type "
			+ " 		,SUM(pid.standard_hours) AS totalStandardHours "
			+ " 		,SUM(pid.billable_hours) AS totalBillableHours "
			+ " 		,SUM(pid.client_holiday) AS totalClientHoliday " + " 		,SUM(pid.mto) AS totalMto "
			+ " 		,SUM(pid.leakage) AS totalLeakage " + " 		,SUM(pid.cost_of_nc) AS totalCostOfNc "
			+ " 	FROM dnc.unbilled_leakage_pid pid " + " 	GROUP BY pid.customer_name "
			+ " 		,pid.location_type" + " 		,pid.project_id " + " 		,pid.project_name "
			+ " 		,pid.project_type ";

	public static final String SQL_SELECT_OFFSHORE_BILLABLE_HOURS = " SELECT t.project_id " + " 	,t.project_name "
			+ " 	,t.customer_id " + " 	,t.customer_name " + " 	,t.project_type "
			+ " 	,sum(t.billable_hours) as billable_hours " + " FROM dnc.Timesheet t "
			+ " WHERE t.location_type = 'Offshore' " + " 	AND t.buffer_flag = 0 " + " 	AND t.primary_flag = 1 "
			+ " GROUP BY t.project_id " + " 	,t.project_name " + " 	,t.customer_id " + " 	,t.customer_name "
			+ " 	,t.project_type " + " ORDER BY t.customer_name " + " 	,t.project_id ";

	public static final String SQL_BUFFER_DATA = "SELECT hcr.vertical_group " + " 	,hcr.mso_or_bu "
			+ " 	,hcr.customer " + " 	,hcr.project_id " + " 	,hcr.project_name " + " 	,hcr.project_type "
			+ " 	,hcr.employee_id " + " 	,hcr.employee_name " + " 	,hcr.designation " + " 	,hcr.status "
			+ " 	,hcr.band_grade " + " 	,hcr.assignment_start_date " + " 	,hcr.assignment_end_date "
			+ " 	,hcr.location_type " + " FROM dnc.head_count_report hcr " + " WHERE hcr.location_type = 'Offshore' "
			+ " 	AND hcr.status != 'Billable' ";

	public static final String SQL_BUFFER_ANALYSIS = " SELECT bd.vertical_group " + " 	,bd.mso_or_bu "
			+ " 	,bd.customer " + " 	,bd.project_id " + " 	,bd.project_name " + " 	,bd.project_type "
			+ " 	,obh.billable_hours AS offshore_billable_hours " + " 	,SUM(bd.jan_staffing) AS buffer_hours "
			+ " 	,pb.percentage_permissible " + " FROM dnc.buffer_data bd "
			+ " LEFT JOIN dnc.offshore_billable_hours obh ON bd.project_id = obh.project_id "
			+ " LEFT JOIN dnc.premissible_buffer pb ON bd.project_id = pb.project_id "
			+ "  WHERE obh.billable_hours != 0 GROUP BY bd.vertical_group " + " 	,bd.mso_or_bu "
			+ "    ,bd.customer " + " 	,bd.project_id " + " 	,bd.project_name " + " 	,bd.project_type "
			+ " 	,obh.billable_hours " + " 	,pb.percentage_permissible ";

	public static final String DNC_CATEGORY_NEGATIVE = "Negative";

	public static final String DNC_CATEGORY_POSITIVE = "Positive";

	public static final int DNC_DEFAULT_INITIAL_ROW_NO = 3;

	public static final int DNC_BD_INITIAL_ROW_NO = 4;

	public static final String EXCEL_TIMESTAMP_FORMAT = "MM/dd/yyyy hh:mm:ss";

	public static final String EXCEL_DATE_FORMAT = "MM/dd/yyyy";

	public static final String SQL_SELECT_DISTINCT_PAY_PERIOD = "SELECT DISTINCT t.pay_period from dnc.Timesheet t";

	public static final String OPEN_XML_FORMAT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static final String EXCEL_EXTENSION = ".xlsx";

	public static final String HTML_FORMAT = "text/html";

	public static final DateTimeFormatter PAYPERIOD_FORMAT = DateTimeFormat.forPattern("MMM d,yyyy");

}
