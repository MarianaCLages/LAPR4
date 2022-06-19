package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.exception.InvalidAnswerFileException;
import eapli.base.surveymanagement.domain.exception.InvalidSurveyException;
import eapli.base.surveymanagement.domain.exception.NoFilesInsideDirectoryException;

public class GenerateStatisticalReportController {

    private final VerifyIfSurveyExistsService surveyExistsService = new VerifyIfSurveyExistsService();
    private final GenerateReportService generateReportService = new GenerateReportService();

    public boolean verifyIfSurveyExists(String code) throws InvalidSurveyException {

        if (!surveyExistsService.findSurvey(code)) {
            throw new InvalidSurveyException();
        }

        return true;
    }

    public void generateReport(String surveyID) throws NoFilesInsideDirectoryException, InvalidAnswerFileException {
        generateReportService.getAllClientAnswersFromSurvey(surveyID);

    }

    public void openFile(String surveyID, int option) {
        if (option == 1) {
            generateReportService.openSurveyFileTxt("docs/Extra/StatisticalReport/ReportSurveyTxt" + surveyID +".txt");
        } else {
            generateReportService.openSurveyFileHTML("docs/Extra/StatisticalReport/ReportSurveyHtml" + surveyID + ".html");
        }
    }


}