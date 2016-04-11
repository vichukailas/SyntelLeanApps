package com.syntel.insurance.dnc.DNCTrackerCore.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.syntel.insurance.dnc.DNCTrackerCore.dao.UnbilledLeakageDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;

public class UnbilledLeakageDAOImpl implements UnbilledLeakageDAO {

	private static final Logger LOG = Logger.getLogger(UnbilledLeakageDAOImpl.class);
	
	private static final String PERSISTENCE_UNIT_NAME = "DNCTrackerCore";
	private static EntityManagerFactory factory;

	@Override
	public void insertIntoDatabase(List<UnbilledLeakagePid> unbilledLeakagePIDList) {

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			em.getTransaction().begin();
			for (UnbilledLeakagePid unbilledLeakagePid : unbilledLeakagePIDList) {
				em.persist(unbilledLeakagePid);
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

}
