package com.syntel.insurance.dnc.DNCTrackerCore.dao;

import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.ProjectTeam;

public interface ProjectTeamDAO {

	void persistProjectTeamList(GenericList<ProjectTeam> projectTeamList);

}
