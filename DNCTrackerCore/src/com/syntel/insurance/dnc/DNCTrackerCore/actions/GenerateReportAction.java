package com.syntel.insurance.dnc.DNCTrackerCore.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.LocalDate;

import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferAnalysis;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferData;
import com.syntel.insurance.dnc.DNCTrackerCore.models.HeadCountReport;
import com.syntel.insurance.dnc.DNCTrackerCore.models.ProjectTeam;
import com.syntel.insurance.dnc.DNCTrackerCore.models.Timesheet;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakageAccount;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;
import com.syntel.insurance.dnc.DNCTrackerCore.util.BufferAnalysisUtil;
import com.syntel.insurance.dnc.DNCTrackerCore.util.Constants;
import com.syntel.insurance.dnc.DNCTrackerCore.util.ExcelGenerationUtil;
import com.syntel.insurance.dnc.DNCTrackerCore.util.HeadCountReportUtil;
import com.syntel.insurance.dnc.DNCTrackerCore.util.PeopleSoftFinanceUtil;
import com.syntel.insurance.dnc.DNCTrackerCore.util.ProjectTeamExtractUtil;
import com.syntel.insurance.dnc.DNCTrackerCore.util.TimesheetExtractUtil;

public class GenerateReportAction {

	public static StringBuffer errors = new StringBuffer();
	public static final String DNC_TRACKER_PATH = "C:\\DNC Tracker\\";
	public static final String DNC_EXTRACT_PATH = "Extracts\\";
	public static final String DNC_REPORT_PATH = "Reports\\";
	public static final String DNC_LOG_PATH = "Logs\\";
	public static final String DNC_DATABASE_PATH = "Database\\";

	private static final Logger LOG = Logger.getLogger(GenerateReportAction.class);

	public static void main(String[] args) {
		try {
			generateDNCReport(null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateDNCReport(File headCount, File timesheetPath) throws IOException {

		GenericList<ProjectTeam> projectTeamList = new GenericList<ProjectTeam>(ProjectTeam.class);
		GenericList<Timesheet> timesheetList = new GenericList<Timesheet>(Timesheet.class);
		List<UnbilledLeakagePid> unbilledLeakagePIDList = new ArrayList<UnbilledLeakagePid>();
		List<UnbilledLeakageAccount> unbilledLeakageAccountList = new ArrayList<UnbilledLeakageAccount>();
		List<BufferData> bufferDataList = new ArrayList<BufferData>();
		GenericList<HeadCountReport> headCountReportList = new GenericList<HeadCountReport>(HeadCountReport.class);
		List<BufferAnalysis> bufferAnalysisList = new ArrayList<BufferAnalysis>();

		LocalDate monthStart = null;
		LocalDate monthEnd = null;

		boolean hcReport = true;

		String projectTeamPath = "C:\\Users\\rajvish\\Desktop\\Managerial\\DNC\\DNC Tool\\Extracts\\2016\\Jan\\New York Life\\NYL_ProjectTeam_97822.xls";

		List<String> payPeriodList = new ArrayList<String>();

		// turning htmlunit warnings and other logging off
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);

		LOG.info("Request received... Starting to process");
		LOG.info("Head Count File Path :: " + headCount);
		LOG.info("Timesheet Folder Path :: " + timesheetPath);

		File createDirs = new File(DNC_TRACKER_PATH);

		if (!createDirs.exists()) {
			LOG.info("Creating base directories");
			createDirs.mkdirs();
			new File(DNC_TRACKER_PATH + DNC_EXTRACT_PATH).mkdir();
			new File(DNC_TRACKER_PATH + DNC_REPORT_PATH).mkdir();
			new File(DNC_TRACKER_PATH + DNC_LOG_PATH).mkdir();
			new File(DNC_TRACKER_PATH + DNC_DATABASE_PATH).mkdir();
		}

		LOG.info("Cleaning up Database tables...");
		cleanUpDatabase();

		LOG.info("Reding Head Count Report File");
		if (hcReport) {
			if (headCount.getName().contains(Constants.EXCEL_EXTENSION)
					&& Constants.OPEN_XML_FORMAT.equalsIgnoreCase(Files.probeContentType(headCount.toPath()))) {
				LOG.info("Head Count Mater sheet is in Excel format");
				headCountReportList = loadHeadCountListFromWorkbook(headCount);
			} else if (Constants.HTML_FORMAT.equalsIgnoreCase(Files.probeContentType(headCount.toPath()))) {
				LOG.info("Head Count Master sheet is in HTML format");
				headCountReportList = loadHeadCountListFromHtml(headCount);
			}
			LOG.info("Total Head Count Records Read :: " + headCountReportList.size());
			LOG.info("Inserting Head Count report into Database");
			insertIntoDatabase(headCountReportList);
		} else {
			LOG.info("Project Team Extract in RAW format");
			projectTeamList = loadProjectTeamListFromHtml(projectTeamPath);
			LOG.info("Total Project Team Records Read :: " + projectTeamList.size());
			insertIntoDatabase(projectTeamList);
		}

		LOG.info("Reading Timesheet Files");
		timesheetList = loadTimesheetListFromHtml(timesheetPath);
		LOG.info("Total Timesheet Records Read :: " + timesheetList.size());
		LOG.info("Inserting Timesheet report into Database");
		insertIntoDatabase(timesheetList);

		payPeriodList = findPayPeriodFromTimesheet();

		LOG.info("Pay Periods to process :: " + payPeriodList);

		if (payPeriodList.size() > 2) {
			LOG.info("Contains more than a month in Timesheet extract! "
					+ "Please process only monthly or fortnight extracts.. Exiting from program");
			System.exit(0);
		} else if (payPeriodList.size() == 2) {
			LOG.info("Processing two Pay Periods... Validating the months");
			String firstDates[] = payPeriodList.get(0).split(" - ");
			String secondDates[] = payPeriodList.get(1).split(" - ");
			LocalDate firstStartDate = Constants.PAYPERIOD_FORMAT.parseLocalDate(firstDates[0]);
			LocalDate firstEndDate = Constants.PAYPERIOD_FORMAT.parseLocalDate(firstDates[1]);
			LocalDate secondStartDate = Constants.PAYPERIOD_FORMAT.parseLocalDate(secondDates[1]);
			LocalDate secondEndDate = Constants.PAYPERIOD_FORMAT.parseLocalDate(secondDates[1]);

			if ((firstStartDate.getYear() == secondStartDate.getYear())
					&& (firstStartDate.getMonthOfYear() == secondStartDate.getMonthOfYear())) {
				LOG.info("Validation successfull, Extract is for a month");
				if (firstStartDate.isBefore(secondStartDate)) {
					monthStart = firstStartDate;
					monthEnd = secondEndDate;
				} else {
					monthStart = secondStartDate;
					monthEnd = firstEndDate;
				}
			} else {
				LOG.info("Extracts contains Pay Periods in different months."
						+ "Please process only monthly or fortnight extracts.. Exiting from program");
				System.exit(0);
			}
		} else if (payPeriodList.size() == 1) {
			LOG.info("Fortnight data to process");
			String dates[] = payPeriodList.get(0).split(" - ");
			monthStart = Constants.PAYPERIOD_FORMAT.parseLocalDate(dates[0]);
			monthEnd = Constants.PAYPERIOD_FORMAT.parseLocalDate(dates[1]);
		}

		LOG.info("Processing data from " + monthStart + " to " + monthEnd);

		LOG.info("Creating Unbilled Leakage (PID) data");
		unbilledLeakagePIDList = createUnbilledLeakagePIDList();
		LOG.info("Unbilled Leakage (PID) Records Size :: " + unbilledLeakagePIDList.size());

		LOG.info("Creating Unbilled Leakage (Account) data");
		unbilledLeakageAccountList = createUnbilledLeakageAccountList();
		LOG.info("Unbilled Leakage (Account) Records Size :: " + unbilledLeakageAccountList.size());

		LOG.info("Calculating Offshore Billable Hours");
		calculateAndInsertOffshoreBillableHours();

		LOG.info("Generating Buffer Data");
		bufferDataList = generateBufferDataList(monthStart, monthEnd);
		LOG.info("Buffer Data Record Size :: " + bufferDataList.size());

		LOG.info("Generating Buffer Analysis Data");
		bufferAnalysisList = generateBufferAnalysisList();
		LOG.info("Buffer Analysis Records Size :: " + bufferAnalysisList.size());

		XSSFWorkbook newWorkbook = new XSSFWorkbook();

		LOG.info("Fetched all necessary information, writing into excel");
		newWorkbook = generateExcelWorkbook(unbilledLeakagePIDList, unbilledLeakageAccountList, bufferDataList,
				bufferAnalysisList);

		LOG.info("Saving the Report file...");
		saveFileIntoDisk(DNC_TRACKER_PATH + DNC_REPORT_PATH,
				"INS DNC Report " + monthStart + "-" + monthEnd + Constants.EXCEL_EXTENSION, newWorkbook);

		LOG.info("Report Generated Successfully! Exiting now...");

	}

	private static void cleanUpDatabase() {
		PeopleSoftFinanceUtil.cleanUpDatabase();
	}

	private static GenericList<HeadCountReport> loadHeadCountListFromWorkbook(File headCount) {
		return HeadCountReportUtil.loadHeadCountListFromWorkbook(headCount);
	}

	private static List<String> findPayPeriodFromTimesheet() {
		return TimesheetExtractUtil.findPayPeriodFromTimesheet();
	}

	private static void saveFileIntoDisk(String path, String fileName, XSSFWorkbook newWorkbook) {

		try {
			// Write the workbook in file system
			File file = new File(path);
			file.mkdirs();
			File outputFile = new File(path + fileName.replaceAll(":", " "));
			FileOutputStream out = new FileOutputStream(outputFile);
			newWorkbook.write(out);
			out.close();
			LOG.info("Saved file to :: " + outputFile);
		} catch (Exception e) {
			LOG.error("Error occured while saving the file..");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		}
	}

	private static XSSFWorkbook generateExcelWorkbook(List<UnbilledLeakagePid> unbilledLeakagePIDList,
			List<UnbilledLeakageAccount> unbilledLeakageAccountList, List<BufferData> bufferDataList,
			List<BufferAnalysis> bufferAnalysisList) {
		return ExcelGenerationUtil.generateExcelWorkbook(unbilledLeakagePIDList, unbilledLeakageAccountList,
				bufferDataList, bufferAnalysisList);
	}

	private static List<BufferAnalysis> generateBufferAnalysisList() {
		return BufferAnalysisUtil.generateBufferAnalysisList();
	}

	private static List<BufferData> generateBufferDataList(LocalDate monthStart, LocalDate monthEnd) {
		return BufferAnalysisUtil.generateBufferDataList(monthStart, monthEnd);
	}

	private static void calculateAndInsertOffshoreBillableHours() {
		TimesheetExtractUtil.calculateAndInsertOffshoreBillableHours();
	}

	private static List<UnbilledLeakageAccount> createUnbilledLeakageAccountList() {
		return TimesheetExtractUtil.createUnbilledLeakageAccountList();
	}

	private static void insertIntoDatabase(GenericList<?> list) {
		PeopleSoftFinanceUtil.insertIntoDatabase(list);
	}

	private static GenericList<HeadCountReport> loadHeadCountListFromHtml(File headCount) {
		return HeadCountReportUtil.loadHeadCountListFromHtml(headCount);
	}

	private static List<UnbilledLeakagePid> createUnbilledLeakagePIDList() {
		return TimesheetExtractUtil.createUnbilledLeakagePIDList();
	}

	private static GenericList<Timesheet> loadTimesheetListFromHtml(File timesheetPath) {
		return TimesheetExtractUtil.loadTimesheetListFromHtml(timesheetPath);
	}

	private static GenericList<ProjectTeam> loadProjectTeamListFromHtml(String filePath) {
		return ProjectTeamExtractUtil.loadProjectTeamListFromHtml(filePath);
	}

}
