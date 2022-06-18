package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.domain.exception.InvalidAnswerFileException;
import eapli.base.surveymanagement.domain.exception.InvalidSurveyException;
import eapli.base.surveymanagement.domain.exception.NoFilesInsideDirectoryException;

public class GenerateStatisticalReportController {

    private final VerifyIfSurveyExistsService surveyExistsService = new VerifyIfSurveyExistsService();
    private final GenerateReportService generateReportService = new GenerateReportService();

    public boolean verifyIfSurveyExists(int id) throws InvalidSurveyException {

        if (!surveyExistsService.findSurvey(id)) {
            throw new InvalidSurveyException();
        }

        return true;
    }

    public void generateReport(int surveyID) throws NoFilesInsideDirectoryException, InvalidAnswerFileException {
        generateReportService.getAllClientAnswersFromSurvey(surveyID);

    }

    public void openFile(int surveyID,int option){
        if (option == 1) {
            generateReportService.openSurveyFileTxt("docs/Extra/StatisticalReport/ReportSurveyTxt" + surveyID);
        } else {
            generateReportService.openSurveyFileHTML("docs/Extra/StatisticalReport/ReportSurveyHtml" + surveyID);
        }
    }


}