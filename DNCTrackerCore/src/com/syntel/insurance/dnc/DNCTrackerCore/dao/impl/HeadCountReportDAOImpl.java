package com.syntel.insurance.dnc.DNCTrackerCore.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.syntel.insurance.dnc.DNCTrackerCore.dao.HeadCountReportDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.HeadCountReport;

public class HeadCountReportDAOImpl implements HeadCountReportDAO {

	private static final Logger LOG = Logger.getLogger(HeadCountReportDAOImpl.class);

	private static final String PERSISTENCE_UNIT_NAME = "DNCTrackerCore";
	private static EntityManagerFactory factory;

	@Override
	public void persistHeadCountReportList(GenericList<HeadCountReport> headCountReportList) {

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			em.getTransaction().begin();
			for (HeadCountReport headCountReport : headCountReportList) {
				em.persist(headCountReport);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Couldn't write Unbilled Leakage records into Database...");
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
	public List<HeadCountReport> fetchHcReportList() {

		List<HeadCountReport> headCountReportList = new ArrayList<HeadCountReport>();

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query q = em.createQuery("Select c from HeadCountReport c");

			headCountReportList = q.getResultList();

		} catch (Exception e) {
			LOG.error("Error occured while fetching Head Count Report List Data from Database..");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} finally {
			if (em != null)
				em.close();
			if (factory != null)
				factory.close();
		}

		return headCountReportList;
	}

}
