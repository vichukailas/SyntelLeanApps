package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.TimesheetDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.impl.TimesheetDAOImpl;
import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.Timesheet;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakageAccount;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;

public class TimesheetExtractUtil {

	private static final Logger LOG = Logger.getLogger(TimesheetExtractUtil.class);

	public static GenericList<Timesheet> loadTimesheetListFromHtml(File timesheetPath) {

		GenericList<Timesheet> timesheetList = new GenericList<Timesheet>(Timesheet.class);

		if (timesheetPath.isDirectory()) {
			for (File timeSheetFile : timesheetPath.listFiles()) {
				try (final WebClient webClient = new WebClient()) {

					// webClient.getCookieManager().clearCookies();
					webClient.getOptions().setThrowExceptionOnScriptError(false);

					HtmlPage report = (HtmlPage) webClient.getPage(timeSheetFile.toURI().toURL());

					HtmlTable reportTable = (HtmlTable) report.getByXPath("//table[1]").get(0);

					for (int i = 1; i < reportTable.getRows().size(); i++) {
						Timesheet timesheet = new Timesheet();
						HtmlTableRow projectTeamRow = reportTable.getRow(i);
						for (int j = 0; j < projectTeamRow.getCells().size(); j++) {
							HtmlTableCell projectTeamCell = projectTeamRow.getCell(j);
							loadTimesheetData(projectTeamCell, timesheet, j);
						}

						timesheetList.add(timesheet);

					}
				} catch (FailingHttpStatusCodeException e) {
					LOG.error("FailingHttpStatusCodeException : Not a valid Timesheet Report HTML file state");
					LOG.error(e.getMessage() + "\n" + e);
					System.exit(0);
				} catch (MalformedURLException e) {
					LOG.error("URL is Malformed for Timesheet Report File");
					LOG.error(e.getMessage() + "\n" + e);
					System.exit(0);
				} catch (IOException e) {
					LOG.error("Unable to read Timesheet File. Please check if the file is open!");
					LOG.error(e.getMessage() + "\n" + e);
					System.exit(0);
				} catch (NumberFormatException e) {
					LOG.error("Number Format Exception...");
					LOG.error(e.getMessage() + "\n" + e);
					System.exit(0);
				}
			}
		}

		return timesheetList;
	}

	private static void loadTimesheetData(HtmlTableCell projectTeamCell, Timesheet timeSheet, int j) {

		String cellValue = null;

		switch (j) {
		case 0: {
			int timeSheetId = 0;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				timeSheetId = Integer.parseInt(cellValue);
			}

			timeSheet.setTimsheetId(timeSheetId);

			break;
		}
		case 1: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setPayPeriod(cellValue);

			break;
		}
		case 2: {
			int employeeId = 0;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				employeeId = Integer.parseInt(cellValue);
			}

			timeSheet.setEmployeeId(employeeId);

			break;
		}
		case 3: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setEmployeeName(cellValue);

			break;
		}
		case 4: {
			int projectId = 0;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				projectId = Integer.parseInt(cellValue);
			}

			timeSheet.setProjectId(projectId);

			break;
		}
		case 5: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setProjectName(cellValue);

			break;
		}
		case 6: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setProjectType(cellValue);

			break;
		}
		case 7: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setReportingManager(cellValue);

			break;
		}
		case 8: {
			int customerId = 0;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				customerId = Integer.parseInt(cellValue);
			}

			timeSheet.setCustomerId(customerId);

			break;
		}
		case 9: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setCustomerName(cellValue);

			break;
		}
		case 10: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setStatus(cellValue);

			break;
		}
		case 11: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				if ("Y".equalsIgnoreCase(cellValue)) {
					timeSheet.setClientTimesheet(Boolean.TRUE);
				} else {
					timeSheet.setClientTimesheet(Boolean.FALSE);
				}
			}

			break;
		}
		case 12: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				if ("Y".equalsIgnoreCase(cellValue)) {
					timeSheet.setBufferFlag(Boolean.TRUE);
				} else {
					timeSheet.setBufferFlag(Boolean.FALSE);
				}
			}

			break;
		}
		case 13: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				if ("Y".equalsIgnoreCase(cellValue)) {
					timeSheet.setPrimaryFlag(Boolean.TRUE);
				} else {
					timeSheet.setPrimaryFlag(Boolean.FALSE);
				}
			}

			break;
		}
		case 14: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setCompanyName(cellValue);

			break;
		}
		case 15: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setLocationType(cellValue);

			break;
		}
		case 16: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setProjectOwningCompany(cellValue);

			break;
		}
		case 17: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setWorkProjectCity(cellValue);

			break;
		}
		case 18: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			timeSheet.setWorkProjectState(cellValue);

			break;
		}
		case 19: {
			cellValue = projectTeamCell.asText().trim();

			timeSheet.setWorkProjectCountry(cellValue);

			break;
		}
		case 20: {
			float workHours = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				workHours = Float.parseFloat(cellValue);
			}

			timeSheet.setWorkHours(workHours);

			break;
		}
		case 21: {
			float jury = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				jury = Float.parseFloat(cellValue);
			}

			timeSheet.setJury(jury);

			break;
		}
		case 22: {
			float weeklyOff = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				weeklyOff = Float.parseFloat(cellValue);
			}

			timeSheet.setWeeklyOff(weeklyOff);

			break;
		}
		case 23: {
			float mto = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				mto = Float.parseFloat(cellValue);
			}

			timeSheet.setMto(mto);

			break;
		}
		case 24: {
			float training = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				training = Float.parseFloat(cellValue);
			}

			timeSheet.setTraining(training);

			break;
		}
		case 25: {
			float bereavement = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				bereavement = Float.parseFloat(cellValue);
			}

			timeSheet.setBereavement(bereavement);

			break;
		}
		case 26: {
			float clientHoliday = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				clientHoliday = Float.parseFloat(cellValue);
			}

			timeSheet.setClientHoliday(clientHoliday);

			break;
		}
		case 27: {
			float unbilledHours = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				unbilledHours = Float.parseFloat(cellValue);
			}

			timeSheet.setUnbilledWorkhours(unbilledHours);

			break;
		}
		case 28: {
			float standardHours = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				standardHours = Float.parseFloat(cellValue);
			}

			timeSheet.setStandardHours(standardHours);

			break;
		}
		case 29: {
			float billableHours = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				billableHours = Float.parseFloat(cellValue);
			}

			timeSheet.setBillableHours(billableHours);

			break;
		}
		case 30: {
			float totalHours = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				totalHours = Float.parseFloat(cellValue);
			}

			timeSheet.setTotalHours(totalHours);

			break;
		}
		case 31: {
			float hoursDifference = 0f;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				hoursDifference = Float.parseFloat(cellValue);
			}

			timeSheet.setHourDifference(hoursDifference);

			break;
		}
		}
	}

	public static List<UnbilledLeakagePid> createUnbilledLeakagePIDList() {

		TimesheetDAO timesheetDAO = new TimesheetDAOImpl();

		List<UnbilledLeakagePid> unbilledLeakagePIDList = new ArrayList<UnbilledLeakagePid>();

		unbilledLeakagePIDList = timesheetDAO.getUnbilledLeakagePID();

		return unbilledLeakagePIDList;
	}

	public static void insertIntoDatabase(GenericList<Timesheet> timesheetList) {

		TimesheetDAO timesheetDAO = new TimesheetDAOImpl();

		if (timesheetList != null && !timesheetList.isEmpty())
			timesheetDAO.persistTimesheetList(timesheetList);
	}

	public static List<UnbilledLeakageAccount> createUnbilledLeakageAccountList() {

		TimesheetDAO timesheetDAO = new TimesheetDAOImpl();

		List<UnbilledLeakageAccount> unbilledLeakageAccountList = new ArrayList<UnbilledLeakageAccount>();

		unbilledLeakageAccountList = timesheetDAO.getUnbilledLeakageAccount();

		return unbilledLeakageAccountList;
	}

	public static void calculateAndInsertOffshoreBillableHours() {

		TimesheetDAO timesheetDAO = new TimesheetDAOImpl();

		timesheetDAO.calculateAndInsertOffshoreBillableHours();
	}

	public static List<String> findPayPeriodFromTimesheet() {

		TimesheetDAO timesheetDAO = new TimesheetDAOImpl();

		return timesheetDAO.findPayPeriodFromTimesheet();
	}

}
