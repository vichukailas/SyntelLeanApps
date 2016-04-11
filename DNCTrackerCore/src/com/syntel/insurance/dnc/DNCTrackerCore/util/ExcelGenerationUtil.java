package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferAnalysis;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferData;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakageAccount;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;

public class ExcelGenerationUtil {
	
	private static final Logger LOG = Logger.getLogger(ExcelGenerationUtil.class);

	/*- 
	Built In Formats of POI with Array Index
	-----------------------------------------------------------------------
	Index- 0     Value- General
	Index- 1     Value- 0
	Index- 2     Value- 0.00
	Index- 3     Value- #,##0
	Index- 4     Value- #,##0.00
	Index- 5     Value- "$"#,##0_);("$"#,##0)
	Index- 6     Value- "$"#,##0_);[Red]("$"#,##0)
	Index- 7     Value- "$"#,##0.00_);("$"#,##0.00)
	Index- 8     Value- "$"#,##0.00_);[Red]("$"#,##0.00)
	Index- 9     Value- 0%
	Index- 10    Value- 0.00%
	Index- 11    Value- 0.00E+00
	Index- 12    Value- # ?/?
	Index- 13    Value- # ??/??
	Index- 14    Value- m/d/yy
	Index- 15    Value- d-mmm-yy
	Index- 16    Value- d-mmm
	Index- 17    Value- mmm-yy
	Index- 18    Value- h:mm AM/PM
	Index- 19    Value- h:mm:ss AM/PM
	Index- 20    Value- h:mm
	Index- 21    Value- h:mm:ss
	Index- 22    Value- m/d/yy h:mm
	
	0x17 - 0x24 reserved for international and undocumented 0x25, "#,##0_);(#,##0)"
	
	Index- 23    Value- reserved-0x17
	Index- 24    Value- reserved-0x18
	Index- 25    Value- reserved-0x19
	Index- 26    Value- reserved-0x1a
	Index- 27    Value- reserved-0x1b
	Index- 28    Value- reserved-0x1c
	Index- 29    Value- reserved-0x1d
	Index- 30    Value- reserved-0x1e
	Index- 31    Value- reserved-0x1f
	Index- 32    Value- reserved-0x20
	Index- 33    Value- reserved-0x21
	Index- 34    Value- reserved-0x22
	Index- 35    Value- reserved-0x23
	Index- 36    Value- reserved-0x24
	
	Index- 37    Value- #,##0_);(#,##0)
	Index- 38    Value- #,##0_);[Red](#,##0)
	Index- 39    Value- #,##0.00_);(#,##0.00)
	Index- 40    Value- #,##0.00_);[Red](#,##0.00)
	Index- 41    Value- _("$"* #,##0_);_("$"* (#,##0);_("$"* "-"_);_(@_)
	Index- 42    Value- _(* #,##0_);_(* (#,##0);_(* "-"_);_(@_)
	Index- 43    Value- _("$"* #,##0.00_);_("$"* (#,##0.00);_("$"* "-"??_);_(@_)
	Index- 44    Value- _(* #,##0.00_);_(* (#,##0.00);_(* "-"??_);_(@_)
	Index- 45    Value- mm:ss
	Index- 46    Value- [h]:mm:ss
	Index- 47    Value- mm:ss.0
	Index- 48    Value- ##0.0E+0
	Index- 49    Value- @
	*/

	public static final String[] BUILT_IN_EXCEL_DATA_FORMAT_ARRAY = BuiltinFormats.getAll();

	private static XSSFWorkbook newWorkbook;
	private static XSSFCellStyle textCellStyle;
	private static XSSFCellStyle numberCellStyle;
	private static XSSFCellStyle decimalCellStyle;
	private static XSSFCellStyle accountingCellStyle;
	private static XSSFCellStyle percentageCellStyle;
	private static XSSFCellStyle dateCellStyle;
	private static XSSFCreationHelper creationHelper;

	public static void defineCellStyles() {

		creationHelper = newWorkbook.getCreationHelper();

		textCellStyle = newWorkbook.createCellStyle();
		numberCellStyle = newWorkbook.createCellStyle();
		decimalCellStyle = newWorkbook.createCellStyle();
		accountingCellStyle = newWorkbook.createCellStyle();
		percentageCellStyle = newWorkbook.createCellStyle();
		dateCellStyle = newWorkbook.createCellStyle();

		textCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		textCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		textCellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
		textCellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
		textCellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
		textCellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		textCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(BUILT_IN_EXCEL_DATA_FORMAT_ARRAY[49]));

		numberCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		numberCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		numberCellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
		numberCellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
		numberCellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
		numberCellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		numberCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(BUILT_IN_EXCEL_DATA_FORMAT_ARRAY[1]));

		decimalCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		decimalCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		decimalCellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
		decimalCellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
		decimalCellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
		decimalCellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		decimalCellStyle
				.setDataFormat(creationHelper.createDataFormat().getFormat(BUILT_IN_EXCEL_DATA_FORMAT_ARRAY[2]));

		accountingCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		accountingCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		accountingCellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
		accountingCellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
		accountingCellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
		accountingCellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		accountingCellStyle
				.setDataFormat(creationHelper.createDataFormat().getFormat(BUILT_IN_EXCEL_DATA_FORMAT_ARRAY[7]));

		percentageCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		percentageCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		percentageCellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
		percentageCellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
		percentageCellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
		percentageCellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		percentageCellStyle
				.setDataFormat(creationHelper.createDataFormat().getFormat(BUILT_IN_EXCEL_DATA_FORMAT_ARRAY[10]));

		dateCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		dateCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		dateCellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
		dateCellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
		dateCellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
		dateCellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(BUILT_IN_EXCEL_DATA_FORMAT_ARRAY[14]));
	}

	public static XSSFWorkbook generateExcelWorkbook(List<UnbilledLeakagePid> unbilledLeakagePIDList,
			List<UnbilledLeakageAccount> unbilledLeakageAccountList, List<BufferData> bufferDataList,
			List<BufferAnalysis> bufferAnalysisList) {

		XSSFSheet sheet = null;

		try {

			URL fileURL = ExcelGenerationUtil.class.getClassLoader().getResource("INS DNC Template.xlsx");
			File f = new File(fileURL.toURI());
			FileInputStream fis = new FileInputStream(f);

			newWorkbook = new XSSFWorkbook(fis);

			int noOfSheets = newWorkbook.getNumberOfSheets();

			defineCellStyles();

			int rowNum = Constants.DNC_DEFAULT_INITIAL_ROW_NO;

			for (int i = 0; i < noOfSheets; i++) {

				sheet = newWorkbook.getSheetAt(i);

				switch (i) {
				case 0: {
					// Unbilled Leakage (PID)
					int finalRowNum = createUnbilledLeakagePidSheet(sheet, rowNum, unbilledLeakagePIDList);

					String range = "B" + (Constants.DNC_DEFAULT_INITIAL_ROW_NO + 1) + ":" + "O" + finalRowNum;
					CellRangeAddress region = CellRangeAddress.valueOf(range);
					short borderStyle = CellStyle.BORDER_MEDIUM;
					RegionUtil.setBorderBottom(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderTop(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderLeft(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderRight(borderStyle, region, sheet, newWorkbook);

					int colIndex = CellReference.convertColStringToIndex("O");
					for (int j = 0; j < colIndex; j++)
						sheet.autoSizeColumn(j);

					break;
				}
				case 1: {
					// Unbilled Leakage (Account)
					int finalRowNum = createUnbilledLeakageAccountSheet(sheet, rowNum, unbilledLeakageAccountList);

					String range = "B" + (Constants.DNC_DEFAULT_INITIAL_ROW_NO + 1) + ":" + "H" + finalRowNum;
					CellRangeAddress region = CellRangeAddress.valueOf(range);
					short borderStyle = CellStyle.BORDER_MEDIUM;
					RegionUtil.setBorderBottom(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderTop(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderLeft(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderRight(borderStyle, region, sheet, newWorkbook);

					int colIndex = CellReference.convertColStringToIndex("H");
					for (int j = 0; j < colIndex; j++)
						sheet.autoSizeColumn(j);

					break;
				}
				case 2: {
					// Buffer Data
					rowNum = Constants.DNC_BD_INITIAL_ROW_NO;
					int finalRowNum = createBufferDataSheet(sheet, rowNum, bufferDataList);

					String range = "B" + (Constants.DNC_BD_INITIAL_ROW_NO + 1) + ":" + "AJ" + finalRowNum;
					String staffingRange = "M" + (Constants.DNC_BD_INITIAL_ROW_NO + 1) + ":" + "X" + finalRowNum;

					CellRangeAddress region = CellRangeAddress.valueOf(range);
					short borderStyle = CellStyle.BORDER_MEDIUM;
					RegionUtil.setBorderBottom(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderTop(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderLeft(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderRight(borderStyle, region, sheet, newWorkbook);

					CellRangeAddress staffingRegion = CellRangeAddress.valueOf(staffingRange);
					RegionUtil.setBorderBottom(borderStyle, staffingRegion, sheet, newWorkbook);
					RegionUtil.setBorderTop(borderStyle, staffingRegion, sheet, newWorkbook);
					RegionUtil.setBorderLeft(borderStyle, staffingRegion, sheet, newWorkbook);
					RegionUtil.setBorderRight(borderStyle, staffingRegion, sheet, newWorkbook);

					int colIndex = CellReference.convertColStringToIndex("AJ");
					for (int j = 0; j < colIndex; j++)
						sheet.autoSizeColumn(j);

					break;
				}
				case 3: {
					// Buffer Analysis
					rowNum = Constants.DNC_DEFAULT_INITIAL_ROW_NO;
					int finalRowNum = createBufferAnalysisSheet(sheet, rowNum, bufferAnalysisList);

					String range = "B" + (Constants.DNC_DEFAULT_INITIAL_ROW_NO + 1) + ":" + "N" + finalRowNum;
					CellRangeAddress region = CellRangeAddress.valueOf(range);
					short borderStyle = CellStyle.BORDER_MEDIUM;
					RegionUtil.setBorderBottom(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderTop(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderLeft(borderStyle, region, sheet, newWorkbook);
					RegionUtil.setBorderRight(borderStyle, region, sheet, newWorkbook);

					int colIndex = CellReference.convertColStringToIndex("N");
					for (int j = 0; j < colIndex; j++)
						sheet.autoSizeColumn(j);

					break;
				}
				}
			}

		} catch (IOException e) {
			LOG.error("Error occured while Generating Excel Sheet.. IOException");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (URISyntaxException e) {
			LOG.error("Error occured while Generating Excel Sheet.. File URI Error");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		}

		return newWorkbook;
	}

	private static int createBufferAnalysisSheet(XSSFSheet bufferAnalysisSheet, int rowNum,
			List<BufferAnalysis> bufferAnalysisList) {

		Row newRow = null;
		Cell newCell = null;

		int cellNum = 0;

		for (BufferAnalysis leakage : bufferAnalysisList) {

			if (leakage.getCostOfNc() != 0) {

				cellNum = 1;

				newRow = bufferAnalysisSheet.createRow(rowNum++);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getVerticalGroup());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getMsoOrBu());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getCustomer());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getProjectId());
				newCell.setCellStyle(numberCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getProjectName());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getProjectType());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getOffshoreBillableHours());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getBufferHours());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getPercentPermissible().doubleValue());
				newCell.setCellStyle(percentageCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getExcessBuffer());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getPercentBuffer());
				newCell.setCellStyle(percentageCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getCostOfNc());
				newCell.setCellStyle(accountingCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getDncCategory());
				newCell.setCellStyle(textCellStyle);
			}

		}
		return rowNum;
	}

	private static int createBufferDataSheet(XSSFSheet bufferDataSheet, int rowNum, List<BufferData> bufferDataList) {

		Row newRow = null;
		Cell newCell = null;

		int cellNum = 0;

		for (BufferData leakage : bufferDataList) {

			cellNum = 1;

			newRow = bufferDataSheet.createRow(rowNum++);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getVerticalGroup());
			newCell.setCellStyle(textCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getMsoOrBu());
			newCell.setCellStyle(textCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getProjectId());
			newCell.setCellStyle(numberCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getEmployeeId());
			newCell.setCellStyle(numberCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getEmployeeName());
			newCell.setCellStyle(textCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getDesignation());
			newCell.setCellStyle(textCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getStatus());
			newCell.setCellStyle(textCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getBandGrade());
			newCell.setCellStyle(textCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getAssignmentStartDate());
			newCell.setCellStyle(dateCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getAssignmentEndDate());
			newCell.setCellStyle(dateCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getLocationType());
			newCell.setCellStyle(textCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getJanStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getFebStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getMarStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getAprStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getMayStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getJunStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getJulStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getAugStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getSepStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getOctStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getNovStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getDecStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getJanYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getFebYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getMarYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getAprYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getMayYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getJunYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getJulYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getAugYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getSepYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getOctYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getNovYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

			newCell = newRow.createCell(cellNum++);
			newCell.setCellValue(leakage.getDecYtdStaffing());
			newCell.setCellStyle(decimalCellStyle);

		}
		return rowNum;
	}

	private static int createUnbilledLeakageAccountSheet(XSSFSheet unbilledLeakageAccountSheet, int rowNum,
			List<UnbilledLeakageAccount> unbilledLeakageAccountList) {

		Row newRow = null;
		Cell newCell = null;

		int cellNum = 0;

		for (UnbilledLeakageAccount leakage : unbilledLeakageAccountList) {

			if (leakage.getCostOfNC() != 0) {

				cellNum = 1;

				newRow = unbilledLeakageAccountSheet.createRow(rowNum++);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getCustomerName());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getLocationType());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getProjectType());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getProjectId());
				newCell.setCellStyle(numberCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getLeakage());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getCostOfNC());
				newCell.setCellStyle(accountingCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getDncCategory());
				newCell.setCellStyle(textCellStyle);
			}
		}
		return rowNum;
	}

	private static int createUnbilledLeakagePidSheet(XSSFSheet unbillegLeakagePidSheet, int rowNum,
			List<UnbilledLeakagePid> unbilledLeakagePIDList) {

		Row newRow = null;
		Cell newCell = null;

		int cellNum = 1;

		for (UnbilledLeakagePid leakage : unbilledLeakagePIDList) {

			if (leakage.getCostOfNc() != 0) {

				cellNum = 1;

				newRow = unbillegLeakagePidSheet.createRow(rowNum++);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getCustomerName());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getId().getEmpId());
				newCell.setCellStyle(numberCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getEmployeeName());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getId().getProjectId());
				newCell.setCellStyle(numberCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getProjectName());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getLocationType());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getProjectType());
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getPrimaryFlag() ? "Y" : "N");
				newCell.setCellStyle(textCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getStandardHours());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getBillableHours());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getClientHoliday());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getMto());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getLeakage());
				newCell.setCellStyle(decimalCellStyle);

				newCell = newRow.createCell(cellNum++);
				newCell.setCellValue(leakage.getCostOfNc());
				newCell.setCellStyle(accountingCellStyle);
			}

		}
		return rowNum;
	}

}
