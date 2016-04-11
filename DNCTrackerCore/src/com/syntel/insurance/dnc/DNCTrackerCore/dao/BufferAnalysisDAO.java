package com.syntel.insurance.dnc.DNCTrackerCore.dao;

import java.util.List;

import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferAnalysis;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferData;
import com.syntel.insurance.dnc.DNCTrackerCore.models.OffshoreBillableHour;

public interface BufferAnalysisDAO {

	void insertOffshoreBillableHoursIntoDatabase(List<OffshoreBillableHour> offshoreBillableHoursList);

	List<BufferData> generateBufferDataCoreList();

	int fetchNoOfHolidays(int month);

	void insertBufferDataIntoDatabase(List<BufferData> bufferDataList);

	List<BufferAnalysis> generateBufferAnalysisList();

	String fetchHolidayDates(int month);

}
