package com.syntel.insurance.dnc.DNCTrackerCore.dao;

import java.util.List;

import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.Timesheet;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakageAccount;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;

public interface TimesheetDAO {

	void persistTimesheetList(GenericList<Timesheet> timesheetList);

	List<UnbilledLeakagePid> getUnbilledLeakagePID();

	List<UnbilledLeakageAccount> getUnbilledLeakageAccount();

	void calculateAndInsertOffshoreBillableHours();

	List<String> findPayPeriodFromTimesheet();

	void cleanupDatabase();

}
