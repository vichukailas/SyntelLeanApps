package com.syntel.insurance.dnc.DNCTrackerCore.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.TimesheetDAO;
import com.syntel.insurance.dnc.DNCTrackerCore.dao.impl.TimesheetDAOImpl;
import com.syntel.insurance.dnc.DNCTrackerCore.list.generics.GenericList;
import com.syntel.insurance.dnc.DNCTrackerCore.models.HeadCountReport;
import com.syntel.insurance.dnc.DNCTrackerCore.models.ProjectTeam;
import com.syntel.insurance.dnc.DNCTrackerCore.models.Timesheet;
import com.syntel.insurance.dnc.DNCTrackerCore.models.UnbilledLeakagePid;

public class PeopleSoftFinanceUtil {

	private static final Logger LOG = Logger.getLogger(PeopleSoftFinanceUtil.class);

	public static boolean validateUser(String username, String password) {

		try (final WebClient webClient = new WebClient()) {

			webClient.getCookieManager().clearCookies();
			webClient.getOptions().setThrowExceptionOnScriptError(false);

			// Get the first page
			final HtmlPage loginPage = webClient.getPage(new URL(Constants.PS_FINANCE_BASE_URL));

			// Get the form that we are dealing with and within that form,
			// find the submit button and the field that we want to change.
			final HtmlForm form = loginPage.getFormByName("login");

			final HtmlSubmitInput submitButton = form.getInputByName("Submit");
			final HtmlTextInput userIdField = form.getInputByName("userid");
			final HtmlPasswordInput passwordField = form.getInputByName("pwd");
			// Change the value of the text field
			userIdField.setValueAttribute(username);
			passwordField.setValueAttribute(password);

			// Now submit the form by clicking the button and get back the
			// second page.
			final HtmlPage homePage = submitButton.click();

			if (Constants.LOGIN_SUCCESS_TITLE_TEXT.equals(homePage.getTitleText())) {
				return true;
			} else {
				return false;
			}

		} catch (FailingHttpStatusCodeException e) {
			LOG.error("Error occured while Validating PeopleSoft Credentials... HTTP Status Code");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (MalformedURLException e) {
			LOG.error("Error occured while Validating PeopleSoft Credentials... Malformed URL");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		} catch (IOException e) {
			LOG.error("Error occured while Validating PeopleSoft Credentials... Unable to read file!");
			LOG.error(e.getMessage() + "\n" + e);
			System.exit(0);
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static void insertIntoDatabase(GenericList<?> list) {

		if (list.getGenericType() == ProjectTeam.class)
			ProjectTeamExtractUtil.insertIntoDatabase((GenericList<ProjectTeam>) list);
		else if (list.getGenericType() == Timesheet.class)
			TimesheetExtractUtil.insertIntoDatabase((GenericList<Timesheet>) list);
		else if (list.getGenericType() == HeadCountReport.class)
			HeadCountReportUtil.insertIntoDatabase((GenericList<HeadCountReport>) list);
		else if (list.getGenericType() == UnbilledLeakagePid.class)
			UnbilledLeakageUtil.insertIntoDatabase((GenericList<UnbilledLeakagePid>) list);

	}

	public static void cleanUpDatabase() {
		TimesheetDAO commonDao = new TimesheetDAOImpl();

		commonDao.cleanupDatabase();
	}

}
