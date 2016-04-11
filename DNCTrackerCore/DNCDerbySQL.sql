SELECT BD.VERTICAL_GROUP, BD.MSO_OR_BU, BD.CUSTOMER, BD.PROJECT_ID, BD.PROJECT_NAME, BD.PROJECT_TYPE
, OBH.BILLABLE_HOURS AS OFFSHORE_BILLABLE_HOURS, SUM(BD.JAN_STAFFING) AS BUFFER_HOURS, PB.PERCENTAGE_PERMISSIBLE FROM
BUFFER_DATA BD LEFT JOIN OFFSHORE_BILLABLE_HOURS OBH ON BD.PROJECT_ID = OBH.PROJECT_ID
LEFT JOIN DNC.PREMISSIBLE_BUFFER PB ON BD.PROJECT_ID = PB.PROJECT_ID
GROUP BY BD.CUSTOMER, BD.PROJECT_ID, BD.PROJECT_NAME, BD.PROJECT_TYPE
, OBH.BILLABLE_HOURS, PB.PERCENTAGE_PERMISSIBLE;

DELETE FROM DNC.BUFFER_DATA;
DELETE FROM DNC.UNBILLED_LEAKAGE_PID;
DELETE FROM DNC.TIMESHEET;
DELETE FROM DNC.OFFSHORE_BILLABLE_HOURS;


SELECT * FROM DNC.HEAD_COUNT_REPORT
SELECT * FROM DNC.PREMISSIBLE_BUFFER

SELECT SUM(BUFFER_DATA.JAN_STAFFING) FROM BUFFER_DATA WHERE BUFFER_DATA.PROJECT_ID = 97169

select * from dnc.timesheet

ALTER TABLE DNC.UNBILLED_LEAKAGE_PID ADD COLUMN PRIMARY_FLAG SMALLINT
ALTER TABLE DNC.BUFFER_DATA ALTER COLUMN STATUS SET DATA TYPE VARCHAR(25);



ALTER USER sysadmin SET search_path to 'DNC'

CREATE TABLE DNC.HOLIDAY (MONTH INTEGER PRIMARY KEY, DATES VARCHAR(50) NOT NULL)

SELECT t.customer_name
	,t.employee_id AS EMP_ID
	,t.employee_name
	,t.project_id AS PROJECT_ID
	,t.project_name
	,t.location_type
	,t.project_type
	,t.primary_flag
	,SUM(t.standard_hours) AS Standard_hours
	,SUM(t.billable_hours) AS Billable_Hours
	,SUM(t.client_holiday) AS Client_Holiday
	,SUM(t.mto) AS Mto
	,CASE 
		WHEN t.location_type = 'Offshore'
			THEN SUM(t.billable_hours) - SUM(t.standard_hours)
		ELSE (SUM(t.billable_hours) + SUM(t.client_holiday) + SUM(t.mto)) - SUM(t.standard_hours)
		END AS LEAKAGE
	,CASE 
		WHEN t.location_type = 'Offshore'
			THEN (SUM(t.billable_hours) - SUM(t.standard_hours)) * 6.63
		ELSE ((SUM(t.billable_hours) + SUM(t.client_holiday) + SUM(t.mto)) - SUM(t.standard_hours)) * 38.61
		END AS COST_OF_NC
FROM dnc.Timesheet t
WHERE t.primary_flag = 1
	AND t.buffer_flag = 0
GROUP BY t.customer_name
	,t.employee_id
	,t.employee_name
	,t.project_id
	,t.project_name
	,t.location_type
	,t.project_type
	,t.primary_flag


