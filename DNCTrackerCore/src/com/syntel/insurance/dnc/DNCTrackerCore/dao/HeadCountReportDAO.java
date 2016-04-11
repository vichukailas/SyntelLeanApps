package com.syntel.insurance.dnc.DNCTrackerCore.dao;

import java.util.List;

import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.HeadCountReport;

public interface HeadCountReportDAO {

	void persistHeadCountReportList(GenericList<HeadCountReport> headCountReportList);

	List<HeadCountReport> fetchHcReportList();

}
