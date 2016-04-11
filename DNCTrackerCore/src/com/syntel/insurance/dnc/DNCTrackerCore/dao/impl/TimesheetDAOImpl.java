package com.syntel.insurance.dnc.DNCTrackerCore.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.syntel.insurance.dnc.DNCTrackerCore.dao.TimesheetDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.OffshoreBillableHour;
import com.syntel.insurance.dnc.DNCTrackerCore.models.Timesheet;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakageAccount;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;
import com.syntel.insurance.dnc.DNCTrackerCore.util.BufferAnalysisUtil;
import com.syntel.insurance.dnc.DNCTrackerCore.util.Constants;
import com.syntel.insurance.dnc.DNCTrackerCore.util.UnbilledLeakageUtil;

public class TimesheetDAOImpl implements TimesheetDAO {

	private static final Logger LOG = Logger.getLogger(TimesheetDAOImpl.class);

	private static final String PERSISTENCE_UNIT_NAME = "DNCTrackerCore";
	private static final String[] TABLE_NAMES_ARRAY = { "BufferData", "HeadCountReport", "OffshoreBillableHour",
			"ProjectTeam", "Timesheet", "UnbilledLeakagePid" };
	private static EntityManagerFactory factory;

	@Override
	public void persistTimesheetList(GenericList<Timesheet> timesheetList) {

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			em.getTransaction().begin();
			for (Timesheet timesheet : timesheetList) {
				em.persist(timesheet);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Couldn't write Timesheet records into Database...");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnbilledLeakagePid> getUnbilledLeakagePID() {

		List<UnbilledLeakagePid> unbilledLeakagePIDList = new ArrayList<UnbilledLeakagePid>();

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query unbilledLeakagePIDQuery = em.createNativeQuery(Constants.SQL_UNBILLED_LEAKAGE_PID,
					UnbilledLeakagePid.class);

			unbilledLeakagePIDList = unbilledLeakagePIDQuery.getResultList();

			UnbilledLeakageUtil.insertIntoDatabase(unbilledLeakagePIDList);

		} catch (Exception e) {
			LOG.error("Error occured while fetching Unbilled Leakage (PID) from Database...");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		return unbilledLeakagePIDList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnbilledLeakageAccount> getUnbilledLeakageAccount() {

		List<UnbilledLeakageAccount> unbilledLeakageAccountList = new ArrayList<UnbilledLeakageAccount>();
		List<Object[]> rawResultList;

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query unbilledLeakageAccountQuery = em.createNativeQuery(Constants.SQL_UNBILLED_LEAKAGE_ACCOUNT);

			rawResultList = unbilledLeakageAccountQuery.getResultList();

			for (Object[] resultElement : rawResultList) {
				String dncCategory = (((double) resultElement[10]) < 0) ? Constants.DNC_CATEGORY_NEGATIVE
						: Constants.DNC_CATEGORY_POSITIVE;
				UnbilledLeakageAccount unbilledLeakageAccount = new UnbilledLeakageAccount((String) resultElement[0],
						(String) resultElement[1], (int) resultElement[2], (String) resultElement[3],
						(String) resultElement[4], (double) resultElement[5], (double) resultElement[6],
						(double) resultElement[7], (double) resultElement[8], (double) resultElement[9],
						(double) resultElement[10], dncCategory);
				unbilledLeakageAccountList.add(unbilledLeakageAccount);
			}
		} catch (Exception e) {
			LOG.error("Error occured while fetching Unbilled Leakage (Account) data from Database...");
			LOG.error(e.getMessage() + "\n" + e);
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		return unbilledLeakageAccountList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void calculateAndInsertOffshoreBillableHours() {

		List<OffshoreBillableHour> offshoreBillableHoursList = new ArrayList<OffshoreBillableHour>();

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query offshoreBillableHoursSelect = em.createNativeQuery(Constants.SQL_SELECT_OFFSHORE_BILLABLE_HOURS,
					OffshoreBillableHour.class);

			offshoreBillableHoursList = offshoreBillableHoursSelect.getResultList();

			BufferAnalysisUtil.insertOffshoreBillableHoursIntoDatabase(offshoreBillableHoursList);

		} catch (Exception e) {
			LOG.error("Error occured while calculating Offshore Billable Hours from Database...");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findPayPeriodFromTimesheet() {

		List<String> payPeriodList = new ArrayList<String>();

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query findPayPeriodQuery = em.createNativeQuery(Constants.SQL_SELECT_DISTINCT_PAY_PERIOD);

			payPeriodList = findPayPeriodQuery.getResultList();

		} catch (Exception e) {
			LOG.error("Couldn't find the pay period's from Database...");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		return payPeriodList;
	}

	@Override
	public void cleanupDatabase() {

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			em.getTransaction().begin();

			for (String tableName : TABLE_NAMES_ARRAY) {
				LOG.info("Cleaning up Table :: " + tableName);
				Query deleteQuery = em.createQuery("Delete from " + tableName);
				deleteQuery.executeUpdate();
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Couldn't clean up Database...");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

	}
}
