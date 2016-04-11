package com.syntel.insurance.dnc.DNCTrackerCore.dao;

import java.util.List;

import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;

public interface UnbilledLeakageDAO {

	void insertIntoDatabase(List<UnbilledLeakagePid> unbilledLeakagePIDList);

}
