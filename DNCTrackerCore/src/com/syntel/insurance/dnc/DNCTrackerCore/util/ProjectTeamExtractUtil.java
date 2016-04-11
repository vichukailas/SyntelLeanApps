package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.ProjectTeamDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.impl.ProjectTeamDAOImpl;
import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.ProjectTeam;
import com.syntel.insurance.dnc.DNCTrackerCore.models.ProjectTeamPK;

public class ProjectTeamExtractUtil {
	
	private static final Logger LOG = Logger.getLogger(ProjectTeamExtractUtil.class);

	public static GenericList<ProjectTeam> loadProjectTeamListFromHtml(String filePath) {

		GenericList<ProjectTeam> projectTeamList = new GenericList<ProjectTeam>(ProjectTeam.class);

		int projectId = 0;

		try (final WebClient webClient = new WebClient()) {

			File file = new File(filePath);

			// webClient.getCookieManager().clearCookies();
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			final HtmlPage report = (HtmlPage) webClient.getPage(file.toURI().toURL());

			final HtmlTable reportTable = (HtmlTable) report.getByXPath("//table[1]").get(0);

			for (int i = 1; i < reportTable.getRows().size(); i++) {
				ProjectTeam projectTeam = new ProjectTeam();
				ProjectTeamPK projectTeamPK = new ProjectTeamPK();
				HtmlTableRow projectTeamRow = reportTable.getRow(i);
				for (int j = 0; j < projectTeamRow.getCells().size(); j++) {
					HtmlTableCell projectTeamCell = projectTeamRow.getCell(j);
					loadProjectTeam(projectTeamCell, projectTeam, projectTeamPK, projectId, j);
				}
				projectTeamPK.setProjectId(projectId);
				projectTeam.setId(projectTeamPK);
				projectTeamList.add(projectTeam);
			}

		} catch (FailingHttpStatusCodeException e) {
			LOG.error("Error occured while Loading Project Team Data... HTTP Status Code Error");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (MalformedURLException e) {
			LOG.error("Error occured while Loading Project Team Data... Malformed URL");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (IOException e) {
			LOG.error("Error occured while Loading Project Team Data... Unable to read file!");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (ParseException e) {
			LOG.error("Error occured while Loading Project Team Data... Parse Exception");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		}

		return projectTeamList;
	}

	private static ProjectTeam loadProjectTeam(HtmlTableCell projectTeamCell, ProjectTeam projectTeam,
			ProjectTeamPK projectTeamPK, int projectId, int j) throws ParseException {

		String cellValue = null;

		switch (j) {
		case 0: {
			int employeeId = 0;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				employeeId = Integer.parseInt(cellValue);
			}

			projectTeamPK.setEmployeeId(employeeId);

			break;
		}
		case 1: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setEmployeeName(cellValue);

			break;
		}
		case 2: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setLob(cellValue);

			break;
		}
		case 3: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setCustomerReference(cellValue);

			break;
		}
		case 4: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setProjectRole(cellValue);

			break;
		}
		case 5: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setCustomerRole(cellValue);

			break;
		}
		case 6: {
			break;
		}
		case 7: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				if ("Y".equalsIgnoreCase(cellValue)) {
					projectTeam.setPrimaryFlag(Boolean.TRUE);
				} else {
					projectTeam.setPrimaryFlag(Boolean.FALSE);
				}
			}

			break;
		}
		case 8: {
			Date startDate = null;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				startDate = Constants.DATE_FORMAT.parse(cellValue);
			}

			projectTeam.setStartDate(startDate);

			break;
		}
		case 9: {
			Date billingDate = null;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				billingDate = Constants.DATE_FORMAT.parse(cellValue);
			}

			projectTeam.setBillingDate(billingDate);

			break;
		}
		case 10: {
			Date endDate = null;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				endDate = Constants.DATE_FORMAT.parse(cellValue);
			}

			projectTeamPK.setEndDate(endDate);

			break;
		}
		case 11: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setReleaseReason(cellValue);

			break;
		}
		case 12: {
			int supervisorId = 0;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				supervisorId = Integer.parseInt(cellValue);
			}

			projectTeam.setSupervisorId(supervisorId);

			break;
		}
		case 13: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setSupervisorName(cellValue);

			break;
		}
		case 14: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setBufferType(cellValue);

			break;
		}
		case 15: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setBufferBenchReason(cellValue);

			break;
		}
		case 16: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setLocationType(cellValue);

			break;
		}
		case 17: {
			int workLocation = 0;

			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty()) {
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");
				workLocation = Integer.parseInt(cellValue);
			}

			projectTeam.setWorkLocation(workLocation);

			break;
		}
		case 18: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setCity(cellValue);

			break;
		}
		case 19: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setDevelopmentUnit(cellValue);

			break;
		}
		case 20: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setUnitDescription(cellValue);

			break;
		}
		case 21: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setVisaType(cellValue);

			break;
		}
		case 22: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setVisaDescription(cellValue);

			break;
		}
		case 23: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setPermissibleActivityCode(cellValue);

			break;
		}
		case 24: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setPermissibleActivityDescription(cellValue);

			break;
		}
		case 25: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setContactSequenceNumber(cellValue);

			break;
		}
		case 26: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setContactName(cellValue);

			break;
		}
		case 27: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setBenchLocationCode(cellValue);

			break;
		}
		case 28: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setBenchLocationCity(cellValue);

			break;
		}
		case 29: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setBand(cellValue);

			break;
		}
		case 30: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setGrade(cellValue);

			break;
		}
		case 31: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setCompanyCode(cellValue);

			break;
		}
		case 32: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setCompanyDescription(cellValue);

			break;
		}
		case 33: {
			cellValue = projectTeamCell.asText().trim();

			if (cellValue != null && !cellValue.isEmpty())
				cellValue = cellValue.replaceAll("\"", "").replace("=", "");

			projectTeam.setAssignmentHistory(cellValue);

			break;
		}
		}
		return projectTeam;
	}

	public static void insertIntoDatabase(GenericList<ProjectTeam> projectTeamList) {

		ProjectTeamDAO projectTeamDAO = new ProjectTeamDAOImpl();

		if (projectTeamList != null && !projectTeamList.isEmpty())
			projectTeamDAO.persistProjectTeamList(projectTeamList);

	}

}
