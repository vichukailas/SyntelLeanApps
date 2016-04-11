package com.syntel.insurance.dnc.DNCTrackerCore.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import com.syntel.insurance.dnc.DNCTrackerCore.dao.ProjectTeamDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.ProjectTeam;

public class ProjectTeamDAOImpl implements ProjectTeamDAO {

	private static final Logger LOG = Logger.getLogger(ProjectTeamDAOImpl.class);

	private static final String PERSISTENCE_UNIT_NAME = "DNCTrackerCore";
	private static EntityManagerFactory factory;

	@Override
	public void persistProjectTeamList(GenericList<ProjectTeam> projectTeamList) {

		EntityManager em = null;

		try {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			em.getTransaction().begin();
			for (ProjectTeam projectTeam : projectTeamList) {
				em.persist(projectTeam);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			LOG.error("Couldn't write Project Team records into Database...");
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
