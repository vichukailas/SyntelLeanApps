package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.syntel.insurance.dnc.DNCTrackerCore.dao.BufferAnalysisDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.impl.BufferAnalysisDAOImpl;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferAnalysis;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferData;
import com.syntel.insurance.dnc.DNCTrackerCore.models.OffshoreBillableHour;

public class BufferAnalysisUtil {

	public static void insertOffshoreBillableHoursIntoDatabase(List<OffshoreBillableHour> offshoreBillableHoursList) {

		BufferAnalysisDAO bufferAnalysis = new BufferAnalysisDAOImpl();

		bufferAnalysis.insertOffshoreBillableHoursIntoDatabase(offshoreBillableHoursList);
	}

	public static List<BufferData> generateBufferDataList(LocalDate monthStart, LocalDate monthEnd) {

		List<BufferData> bufferDataCoreList = new ArrayList<BufferData>();
		List<BufferData> bufferDataList = new ArrayList<BufferData>();

		BufferAnalysisDAO bufferAnalysis = new BufferAnalysisDAOImpl();

		bufferDataCoreList = bufferAnalysis.generateBufferDataCoreList();

		bufferDataList = calculateStaffingPerEmployee(bufferDataCoreList, monthStart, monthEnd);

		insertBufferDataIntoDatabase(bufferDataList);

		return bufferDataList;
	}

	private static void insertBufferDataIntoDatabase(List<BufferData> bufferDataList) {

		BufferAnalysisDAO bufferAnalysis = new BufferAnalysisDAOImpl();

		bufferAnalysis.insertBufferDataIntoDatabase(bufferDataList);

	}

	private static List<BufferData> calculateStaffingPerEmployee(List<BufferData> bufferDataCoreList,
			LocalDate monthStart, LocalDate monthEnd) {

		List<BufferData> bufferDataList = new ArrayList<BufferData>();

		Map<Integer, String> holidayDatesMap = new HashMap<Integer, String>();

		for (BufferData buffer : bufferDataCoreList) {

			LocalDate assignStartDate = (new DateTime(buffer.getAssignmentStartDate())).toLocalDate();
			LocalDate assignEndDate = (new DateTime(buffer.getAssignmentEndDate())).toLocalDate();

			boolean currMonthReached = false;

			for (int month = 1; month <= 12; month++) {

				float staffingInDays = 0.0f;
				float noOfHolidays = 0.0f;

				String holidayDates = null;

				LocalDate startDate = assignStartDate;
				LocalDate endDate = assignEndDate;
				LocalDate processingMonthStart = new LocalDate(monthStart.getYear(), month, 1);
				LocalDate processingMonthEnd = processingMonthStart.dayOfMonth().withMaximumValue();
				
				if ((processingMonthStart.getMonthOfYear() == monthStart.getMonthOfYear())
						&& (processingMonthStart.getYear() == monthStart.getYear()))
					currMonthReached = true;

				if (!currMonthReached
						&& (startDate.isAfter(processingMonthEnd) || endDate.isBefore(processingMonthStart))) {
					staffingInDays = 0.0f;
					continue;
				} else if (currMonthReached && (startDate.isAfter(monthEnd) || endDate.isBefore(monthStart))) {
					staffingInDays = 0.0f;
					continue;
				}

				if (!currMonthReached && startDate.isBefore(processingMonthStart))
					startDate = processingMonthStart;
				else if (currMonthReached && startDate.isBefore(monthStart))
					startDate = monthStart;

				if (!currMonthReached && endDate.isAfter(processingMonthEnd))
					endDate = processingMonthEnd.plusDays(1);
				else if (currMonthReached && endDate.isAfter(monthEnd))
					endDate = monthEnd.plusDays(1);

				float weekDays = noOfWeekDays(startDate, endDate);

				if (!holidayDatesMap.containsKey(month)) {
					holidayDates = fetchHolidayDates(month);
					holidayDatesMap.put(month, holidayDates);
				} else {
					holidayDates = holidayDatesMap.get(month);
				}
				if (holidayDates != null) {
					int count = 0;
					if (holidayDates.contains(",")) {
						String dates[] = holidayDates.split(",");
						for (String date : dates) {
							if (Integer.parseInt(date) >= startDate.getDayOfMonth()
									|| Integer.parseInt(date) <= endDate.getDayOfMonth())
								count++;
						}
					} else
						count = 1;
					noOfHolidays = count;
				}

				currMonthReached = false;
				staffingInDays = weekDays - noOfHolidays;

				switch (month) {
				case 1: {
					buffer.setJanStaffing(staffingInDays);
					buffer.setJanYtdStaffing(staffingInDays);
					break;
				}
				case 2: {
					float ytdStaffing = buffer.getJanYtdStaffing() + staffingInDays;
					buffer.setFebStaffing(staffingInDays);
					buffer.setFebYtdStaffing(ytdStaffing);
					break;
				}
				case 3: {
					float ytdStaffing = buffer.getFebYtdStaffing() + staffingInDays;
					buffer.setMarStaffing(staffingInDays);
					buffer.setMarYtdStaffing(ytdStaffing);
					break;
				}
				case 4: {
					float ytdStaffing = buffer.getMarYtdStaffing() + staffingInDays;
					buffer.setAprStaffing(staffingInDays);
					buffer.setAprYtdStaffing(ytdStaffing);
					break;
				}
				case 5: {
					float ytdStaffing = buffer.getAprYtdStaffing() + staffingInDays;
					buffer.setMayStaffing(staffingInDays);
					buffer.setMayYtdStaffing(ytdStaffing);
					break;
				}
				case 6: {
					float ytdStaffing = buffer.getMayYtdStaffing() + staffingInDays;
					buffer.setJunStaffing(staffingInDays);
					buffer.setJunYtdStaffing(ytdStaffing);
					break;
				}
				case 7: {
					float ytdStaffing = buffer.getJunYtdStaffing() + staffingInDays;
					buffer.setJulStaffing(staffingInDays);
					buffer.setJulYtdStaffing(ytdStaffing);
					break;
				}
				case 8: {
					float ytdStaffing = buffer.getJulYtdStaffing() + staffingInDays;
					buffer.setAugStaffing(staffingInDays);
					buffer.setAugYtdStaffing(ytdStaffing);
					break;
				}
				case 9: {
					float ytdStaffing = buffer.getAugYtdStaffing() + staffingInDays;
					buffer.setSepStaffing(staffingInDays);
					buffer.setSepYtdStaffing(ytdStaffing);
					break;
				}
				case 10: {
					float ytdStaffing = buffer.getSepYtdStaffing() + staffingInDays;
					buffer.setOctStaffing(staffingInDays);
					buffer.setOctYtdStaffing(ytdStaffing);
					break;
				}
				case 11: {
					float ytdStaffing = buffer.getOctYtdStaffing() + staffingInDays;
					buffer.setNovStaffing(staffingInDays);
					buffer.setNovYtdStaffing(ytdStaffing);
					break;
				}
				case 12: {
					float ytdStaffing = buffer.getNovYtdStaffing() + staffingInDays;
					buffer.setDecStaffing(staffingInDays);
					buffer.setDecYtdStaffing(ytdStaffing);
					break;
				}
				}
				if (currMonthReached)
					break;
			}
			bufferDataList.add(buffer);
		}

		return bufferDataList;
	}

	private static String fetchHolidayDates(int month) {

		String holidayDates = null;

		BufferAnalysisDAO bufferAnalysisDAO = new BufferAnalysisDAOImpl();

		holidayDates = bufferAnalysisDAO.fetchHolidayDates(month);

		return holidayDates;
	}

	/*
	 * Auto-generated method stub
	 * 
	 * int noOfHolidays = 0;
	 * 
	 * BufferAnalysisDAO bufferAnalysisDAO = new BufferAnalysisDAOImpl();
	 * 
	 * noOfHolidays = bufferAnalysisDAO.fetchNoOfHolidays(month);
	 * 
	 * return noOfHolidays; }
	 */

	private static float noOfWeekDays(LocalDate startDate, LocalDate endDate) {

		float weekDays = 0.0f;

		LocalDate weekday = startDate;

		if (startDate.getDayOfWeek() == DateTimeConstants.SATURDAY
				|| startDate.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			weekday = weekday.plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY);
		}

		while (weekday.isBefore(endDate)) {

			weekDays++;

			if (weekday.getDayOfWeek() == DateTimeConstants.FRIDAY)
				weekday = weekday.plusDays(3);
			else
				weekday = weekday.plusDays(1);
		}

		return weekDays;
	}

	public static List<BufferAnalysis> generateBufferAnalysisList() {

		List<BufferAnalysis> bufferAnalysisCoreList = new ArrayList<BufferAnalysis>();
		List<BufferAnalysis> bufferAnalysisList = new ArrayList<BufferAnalysis>();

		BufferAnalysisDAO bufferAnalysisDAO = new BufferAnalysisDAOImpl();

		bufferAnalysisCoreList = bufferAnalysisDAO.generateBufferAnalysisList();

		bufferAnalysisList = calculateCostOfNC(bufferAnalysisCoreList);

		return bufferAnalysisList;
	}

	private static List<BufferAnalysis> calculateCostOfNC(List<BufferAnalysis> bufferAnalysisCoreList) {

		List<BufferAnalysis> bufferAnalysisList = new ArrayList<BufferAnalysis>();

		for (BufferAnalysis bufferAnalysis : bufferAnalysisCoreList) {

			double offshoreBillableHours = bufferAnalysis.getOffshoreBillableHours();
			double bufferHours = bufferAnalysis.getBufferHours();
			BigDecimal percentPermissible = bufferAnalysis.getPercentPermissible();

			float excessBuffer = 0.0f;
			float percentBuffer = 0.0f;
			float costOfNc = 0.0f;
			String dncCategory = null;

			if (offshoreBillableHours != 0.0d && bufferHours != 0.0d && percentPermissible != BigDecimal.ZERO) {

				excessBuffer = (float) (bufferHours - (offshoreBillableHours * percentPermissible.doubleValue()));
				percentBuffer = (float) (bufferHours / offshoreBillableHours);
				costOfNc = (float) ((-1) * excessBuffer * 3.77);
				if (costOfNc < 0) {
					dncCategory = Constants.DNC_CATEGORY_NEGATIVE;
				} else {
					dncCategory = Constants.DNC_CATEGORY_POSITIVE;
				}

				bufferAnalysis.setExcessBuffer(excessBuffer);
				bufferAnalysis.setPercentBuffer(percentBuffer);
				bufferAnalysis.setCostOfNc(costOfNc);
				bufferAnalysis.setDncCategory(dncCategory);

			}
			bufferAnalysisList.add(bufferAnalysis);
		}

		return bufferAnalysisList;
	}

}
