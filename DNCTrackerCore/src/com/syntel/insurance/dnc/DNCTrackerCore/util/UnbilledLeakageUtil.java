package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.util.List;

import com.syntel.insurance.dnc.DNCTrackerCore.dao.UnbilledLeakageDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.impl.UnbilledLeakageDAOImpl;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;

/**
 * @author rajvish
 *
 */
public class UnbilledLeakageUtil {

	public static void insertIntoDatabase(List<UnbilledLeakagePid> unbilledLeakagePIDList) {

		UnbilledLeakageDAO d = new UnbilledLeakageDAOImpl();

		if (unbilledLeakagePIDList != null && !unbilledLeakagePIDList.isEmpty())
			d.insertIntoDatabase(unbilledLeakagePIDList);

	}

}
