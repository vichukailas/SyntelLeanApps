package com.syntel.insurance.dnc.DNCTrackerCore.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.syntel.insurance.dnc.DNCTrackerCore.dao.BufferAnalysisDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferAnalysis;
import com.syntel.insurance.dnc.DNCTrackerCore.models.BufferData;
import com.syntel.insurance.dnc.DNCTrackerCore.models.Holiday;
import com.syntel.insurance.dnc.DNCTrackerCore.models.HolidaysCount;
import com.syntel.insurance.dnc.DNCTrackerCore.models.OffshoreBillableHour;
import com.syntel.insurance.dnc.DNCTrackerCore.util.Constants;

public class BufferAnalysisDAOImpl implements BufferAnalysisDAO {

	private static final Logger LOG = Logger.getLogger(BufferAnalysisDAOImpl.class);

	private static final String PERSISTENCE_UNIT_NAME = "DNCTrackerCore";
	private static EntityManagerFactory factory;

	@Override
	public void insertOffshoreBillableHoursIntoDatabase(List<OffshoreBillableHour> offshoreBillableHoursList) {

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			em.getTransaction().begin();
			for (OffshoreBillableHour offshoreBillableHour : offshoreBillableHoursList) {
				em.persist(offshoreBillableHour);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Error occured while inserting Offshore Billable Hours into Database..");
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
	public List<BufferData> generateBufferDataCoreList() {

		List<BufferData> bufferDataCoreList = new ArrayList<BufferData>();

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query bufferDataCoreListQuery = em.createNativeQuery(Constants.SQL_BUFFER_DATA, BufferData.class);

			bufferDataCoreList = bufferDataCoreListQuery.getResultList();

		} catch (Exception e) {
			LOG.error("Error occured while fetching Core Buffer Data from Database..");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		return bufferDataCoreList;
	}

	@Override
	public int fetchNoOfHolidays(int month) {

		HolidaysCount holidays = null;

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			holidays = em.find(HolidaysCount.class, month);

		} catch (Exception e) {
			LOG.error("Error occured while fetching No of Holidays from Database..");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		if (holidays != null)
			return holidays.getNoOfHolidays();
		else
			return 0;
	}

	@Override
	public void insertBufferDataIntoDatabase(List<BufferData> bufferDataList) {

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			em.getTransaction().begin();
			for (BufferData buffer : bufferDataList) {
				em.persist(buffer);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Error occured while inserting Core Buffer Data into Database..");
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
	public List<BufferAnalysis> generateBufferAnalysisList() {

		List<BufferAnalysis> bufferAnalysisCoreList = new ArrayList<BufferAnalysis>();
		List<Object[]> rawResultList;

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query bufferAnalysisQuery = em.createNativeQuery(Constants.SQL_BUFFER_ANALYSIS);

			rawResultList = bufferAnalysisQuery.getResultList();

			for (Object[] resultElement : rawResultList) {
				BufferAnalysis bufferAnalysis = new BufferAnalysis((String) resultElement[0], (String) resultElement[1],
						(String) resultElement[2], (int) resultElement[3], (String) resultElement[4],
						(String) resultElement[5], (double) (resultElement[6] == null ? 0.0d : resultElement[6]),
						(double) (resultElement[7] == null ? 0.0d : resultElement[7]),
						(BigDecimal) (resultElement[8] == null ? BigDecimal.ZERO
								: BigDecimal.valueOf(((BigDecimal) resultElement[8]).doubleValue() / 100)));
				bufferAnalysisCoreList.add(bufferAnalysis);
			}
		} catch (Exception e) {
			LOG.error("Error occured while fetching Buffer Analysis Data from Database..");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		return bufferAnalysisCoreList;
	}

	@Override
	public String fetchHolidayDates(int month) {

		Holiday holiday = null;

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			holiday = em.find(Holiday.class, month);

		} catch (Exception e) {
			LOG.error("Error occured while fetching Holiday Dates from Database..");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		if (holiday != null)
			return holiday.getDates();
		else
			return null;
	}

}
