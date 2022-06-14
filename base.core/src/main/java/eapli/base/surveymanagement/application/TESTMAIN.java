package eapli.base.surveymanagement.application;

public class TESTMAIN {

    public static void main(String[] args) {
        GenerateReportService generateReportService = new GenerateReportService();

        try {
            int id = 52;

            generateReportService.getAllClientAnswersFromSurvey(id);

            generateReportService.openSurveyFile("docs/Extra/StatisticalReport/ReportSurvey" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
