package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.HeadCountReportDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.impl.HeadCountReportDAOImpl;
import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.HeadCountReport;

public class HeadCountReportUtil {

	private static final Logger LOG = Logger.getLogger(HeadCountReportUtil.class);

	public static GenericList<HeadCountReport> loadHeadCountListFromHtml(File headCount) {

		GenericList<HeadCountReport> headCountReportList = new GenericList<HeadCountReport>(HeadCountReport.class);

		try (final WebClient webClient = new WebClient()) {

			// webClient.getCookieManager().clearCookies();
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			final HtmlPage report = (HtmlPage) webClient.getPage(headCount.toURI().toURL());

			final HtmlTable reportTable = (HtmlTable) report
					.getByXPath("//table[1]/tbody/tr/td/table/tbody/tr[2]/td/table").get(0);

			for (int i = 1; i < reportTable.getRows().size(); i++) {
				HeadCountReport headCountReport = new HeadCountReport();
				HtmlTableRow headCountReportRow = reportTable.getRow(i);
				for (int j = 0; j < headCountReportRow.getCells().size(); j++) {
					HtmlTableCell headCountReportCell = headCountReportRow.getCell(j);
					loadHeadCount(headCountReportCell, headCountReport, j);
				}
				headCountReportList.add(headCountReport);
			}

		} catch (FailingHttpStatusCodeException e) {
			LOG.error("FailingHttpStatusCodeException : Not a valid Head Count Report HTML file state");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (MalformedURLException e) {
			LOG.error("URL is Malformed for Head Count Report File");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (IOException e) {
			LOG.error("Unable to read Head Count Report File. Please check if the file is open!");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (ParseException e) {
			LOG.error("Unable to parse value");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		}

		return headCountReportList;
	}

	private static void loadHeadCount(HtmlTableCell headCountReportCell, HeadCountReport headCountReport, int j)
			throws ParseException {

		String cellValue = null;

		switch (j) {
		case 0: {
			int employeeId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				employeeId = Integer.parseInt(cellValue);
			}

			headCountReport.setEmployeeId(employeeId);

			break;
		}
		case 1: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setEmployeeName(cellValue);

			break;
		}
		case 2: {

			int projectId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				projectId = Integer.parseInt(cellValue);
			}

			headCountReport.setProjectId(projectId);

			break;
		}
		case 3: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setProjectName(cellValue);

			break;
		}
		case 4: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setProjectLob(cellValue);

			break;
		}
		case 5: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setProjectType(cellValue);

			break;
		}
		case 6: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setProjectCategory(cellValue);

			break;
		}
		case 7: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setStatus(cellValue);

			break;
		}
		case 8: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setBusinessType(cellValue);

			break;
		}
		case 9: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setLob(cellValue);

			break;
		}
		case 10: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setCustomer(cellValue);

			break;
		}
		case 11: {
			int rmId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				rmId = Integer.parseInt(cellValue);
			}

			headCountReport.setRmId(rmId);

			break;
		}
		case 12: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setRmName(cellValue);

			break;
		}
		case 13: {
			int offshoreAmId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				offshoreAmId = Integer.parseInt(cellValue);
			}

			headCountReport.setOffshoreAmId(offshoreAmId);

			break;
		}
		case 14: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setOffshoreAmName(cellValue);

			break;
		}
		case 15: {
			int amPmId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				amPmId = Integer.parseInt(cellValue);
			}

			headCountReport.setAmPmId(amPmId);

			break;
		}
		case 16: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setAmPmName(cellValue);

			break;
		}
		case 17: {
			int emDmId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				emDmId = Integer.parseInt(cellValue);
			}

			headCountReport.setEmDmId(emDmId);

			break;
		}
		case 18: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setEmDmName(cellValue);

			break;
		}
		case 19: {
			int DdId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				DdId = Integer.parseInt(cellValue);
			}

			headCountReport.setDdId(DdId);

			break;
		}
		case 20: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setDdName(cellValue);

			break;
		}
		case 21: {
			int verticalHeadId = 0;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				verticalHeadId = Integer.parseInt(cellValue);
			}

			headCountReport.setVerticalHeadId(verticalHeadId);

			break;
		}
		case 22: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setVerticalHeadName(cellValue);

			break;
		}
		case 23: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setCostCenter(cellValue);

			break;
		}
		case 24: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setVerticalGroup(cellValue);

			break;
		}
		case 25: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setVertical(cellValue);

			break;
		}
		case 26: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setDesignation(cellValue);

			break;
		}
		case 27: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setBandGrade(cellValue);

			break;
		}
		case 28: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setVisaType(cellValue);

			break;
		}
		case 29: {
			Date visaExpDate = null;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				visaExpDate = Constants.DATE_FORMAT.parse(cellValue);
			}

			headCountReport.setVisaExpDate(visaExpDate);

			break;
		}
		case 30: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setBillability(cellValue);

			break;
		}
		case 31: {
			Date assignmentStartDate = null;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				assignmentStartDate = Constants.DATE_FORMAT.parse(cellValue);
			}

			headCountReport.setAssignmentStartDate(assignmentStartDate);

			break;
		}
		case 32: {
			Date assignmentEndDate = null;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				assignmentEndDate = Constants.DATE_FORMAT.parse(cellValue);
			}

			headCountReport.setAssignmentEndDate(assignmentEndDate);

			break;
		}
		case 33: {
			Date billingStartDate = null;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				billingStartDate = Constants.DATE_FORMAT.parse(cellValue);
			}

			headCountReport.setBillingStartDate(billingStartDate);

			break;
		}
		case 34: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setProjectCountryCode(cellValue);

			break;
		}
		case 35: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setWorkCountryCode(cellValue);

			break;
		}
		case 36: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setEmployeeCountryCode(cellValue);

			break;
		}
		case 37: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setEmployeeRegion(cellValue);

			break;
		}
		case 38: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setWorkLocation(cellValue);

			break;
		}
		case 39: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setLocationType(cellValue);

			break;
		}
		case 40: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setCompanyName(cellValue);

			break;
		}
		case 41: {
			boolean sowRecord = false;

			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				if ("Yes".equalsIgnoreCase(cellValue))
					sowRecord = true;
			}

			headCountReport.setSowRecord(sowRecord);

			break;
		}
		case 42: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setLocation(cellValue);

			break;
		}
		case 43: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setUnitName(cellValue);

			break;
		}
		case 44: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setMsoOrBu(cellValue);

			break;
		}
		case 45: {
			cellValue = headCountReportCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			headCountReport.setDeliveryLocation(cellValue);

			break;
		}
		}
	}

	public static void insertIntoDatabase(GenericList<HeadCountReport> headCountReportList) {

		HeadCountReportDAO headCountReportDAO = new HeadCountReportDAOImpl();

		if (headCountReportList != null && !headCountReportList.isEmpty())
			headCountReportDAO.persistHeadCountReportList(headCountReportList);

	}

	public static GenericList<HeadCountReport> loadHeadCountListFromWorkbook(File headCountFile) {

		GenericList<HeadCountReport> headCountReportList = new GenericList<HeadCountReport>(HeadCountReport.class);

		FileInputStream file;
		try {
			file = new FileInputStream(headCountFile);
			XSSFWorkbook headCountWorkbook = new XSSFWorkbook(file);

			XSSFSheet headCountSheet = headCountWorkbook.getSheetAt(1);
			Iterator<Row> rowIterator = headCountSheet.iterator();
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();

				HeadCountReport headCount = new HeadCountReport();

				// Excluding headers from processing.
				if (row.getRowNum() == 0) {
					continue;
				}

				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly

					switch (cell.getColumnIndex()) {

					case 0: {
						int employeeId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null)
							employeeId = (Double.valueOf(cellValue)).intValue();

						headCount.setEmployeeId(employeeId);

						break;
					}
					case 1: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setEmployeeName(cellValue);

						break;
					}
					case 10: {

						int projectId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}
						if (cellValue != null)
							projectId = (Double.valueOf(cellValue)).intValue();

						headCount.setProjectId(projectId);

						break;
					}
					case 11: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setProjectName(cellValue);

						break;
					}
					case 12: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setProjectLob(cellValue);

						break;
					}
					case 14: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setProjectType(cellValue);

						break;
					}
					case 15: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setProjectCategory(cellValue);

						break;
					}
					case 16: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setStatus(cellValue);

						break;
					}
					case 17: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setBusinessType(cellValue);

						break;
					}
					case 18: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setLob(cellValue);

						break;
					}
					case 13: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setCustomer(cellValue);

						break;
					}
					case 19: {
						int rmId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null)
							rmId = (Double.valueOf(cellValue)).intValue();

						headCount.setRmId(rmId);

						break;
					}
					case 20: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setRmName(cellValue);

						break;
					}
					case 21: {
						int offshoreAmId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null)
							offshoreAmId = (Double.valueOf(cellValue)).intValue();

						headCount.setOffshoreAmId(offshoreAmId);

						break;
					}
					case 22: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setOffshoreAmName(cellValue);

						break;
					}
					case 23: {
						int amPmId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null)
							amPmId = (Double.valueOf(cellValue)).intValue();

						headCount.setAmPmId(amPmId);

						break;
					}
					case 24: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setAmPmName(cellValue);

						break;
					}
					case 25: {
						int emDmId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null)
							emDmId = (Double.valueOf(cellValue)).intValue();

						headCount.setEmDmId(emDmId);

						break;
					}
					case 26: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setEmDmName(cellValue);

						break;
					}
					case 27: {
						int ddId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null)
							ddId = (Double.valueOf(cellValue)).intValue();

						headCount.setDdId(ddId);

						break;
					}
					case 28: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setDdName(cellValue);

						break;
					}
					case 29: {
						int verticalHeadId = 0;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null)
							verticalHeadId = (Double.valueOf(cellValue)).intValue();

						headCount.setVerticalHeadId(verticalHeadId);

						break;
					}
					case 30: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setVerticalHeadName(cellValue);

						break;
					}
					case 31: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setCostCenter(cellValue);

						break;
					}
					case 8: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setVerticalGroup(cellValue);

						break;
					}
					case 9: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setVertical(cellValue);

						break;
					}
					case 7: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setDesignation(cellValue);

						break;
					}
					case 6: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setBandGrade(cellValue);

						break;
					}
					case 32: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setVisaType(cellValue);

						break;
					}
					case 33: {
						Date visaExpDate = null;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null && DateUtil.isCellDateFormatted(cell)) {
							visaExpDate = DateUtil.getJavaDate(Double.valueOf(cellValue));
						}

						headCount.setVisaExpDate(visaExpDate);

						break;
					}
					case 34: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setBillability(cellValue);

						break;
					}
					case 35: {
						Date assignmentStartDate = null;
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null && DateUtil.isCellDateFormatted(cell)) {
							assignmentStartDate = DateUtil.getJavaDate(Double.valueOf(cellValue));
						}

						headCount.setAssignmentStartDate(assignmentStartDate);

						break;
					}
					case 37: {
						Date assignmentEndDate = null;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}
						if (cellValue != null && DateUtil.isCellDateFormatted(cell)) {
							assignmentEndDate = DateUtil.getJavaDate(Double.valueOf(cellValue));
						}

						headCount.setAssignmentEndDate(assignmentEndDate);

						break;
					}
					case 36: {
						Date billingStartDate = null;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						if (cellValue != null && DateUtil.isCellDateFormatted(cell)) {
							billingStartDate = DateUtil.getJavaDate(Double.valueOf(cellValue));
						}

						headCount.setBillingStartDate(billingStartDate);

						break;
					}
					case 38: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setProjectCountryCode(cellValue);

						break;
					}
					case 39: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setWorkCountryCode(cellValue);

						break;
					}
					case 40: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setEmployeeCountryCode(cellValue);

						break;
					}
					case 41: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setEmployeeRegion(cellValue);

						break;
					}
					case 42: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setWorkLocation(cellValue);

						break;
					}
					case 43: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setLocationType(cellValue);

						break;
					}
					case 44: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setCompanyName(cellValue);

						break;
					}
					case 45: {
						boolean sowRecord = false;

						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}
						if ("Yes".equalsIgnoreCase(cellValue))
							sowRecord = true;

						headCount.setSowRecord(sowRecord);

						break;
					}
					case 46: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setLocation(cellValue);

						break;
					}
					case 47: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setUnitName(cellValue);

						break;
					}
					case 48: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setMsoOrBu(cellValue);

						break;
					}
					case 49: {
						String cellValue = null;

						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
							cellValue = cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							cellValue = cell.getNumericCellValue() + "";
						}

						headCount.setDeliveryLocation(cellValue);

						break;
					}

					}

				}
				headCountReportList.add(headCount);
			}
		} catch (FileNotFoundException e) {
			LOG.error("Head Count Report File Not Found");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (IOException e) {
			LOG.error("Unable to read Head Count Report File. Please check if the file is open!");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		}

		return headCountReportList;
	}

	public static List<HeadCountReport> fetchHcReportList() {

		List<HeadCountReport> headCountReportList = new ArrayList<HeadCountReport>();

		HeadCountReportDAO headCountReportDAO = new HeadCountReportDAOImpl();

		headCountReportList = headCountReportDAO.fetchHcReportList();

		return headCountReportList;
	}

}
