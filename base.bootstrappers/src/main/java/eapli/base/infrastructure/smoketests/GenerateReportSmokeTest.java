package eapli.base.infrastructure.smoketests;

import eapli.base.surveymanagement.application.GenerateStatisticalReportController;
import eapli.base.surveymanagement.domain.exception.InvalidAnswerFileException;
import eapli.base.surveymanagement.domain.exception.InvalidSurveyException;
import eapli.base.surveymanagement.domain.exception.NoFilesInsideDirectoryException;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateReportSmokeTest implements Action {

    private static final Logger LOGGER = LogManager.getLogger(SurveySmokeTest.class);
    private static final GenerateStatisticalReportController controller = new GenerateStatisticalReportController();

    @Override
    public boolean execute() {
        generateReport();

        if (generateReport()) {
            LOGGER.info("»»» Generate statistical report SUCCESS!");
            return true;
        }

        LOGGER.info("»»» Generate statistical report ERROR!");
        return false;
    }

    private boolean generateReport() {
        String surveyId = "S01";

        try {
            controller.verifyIfSurveyExists(surveyId);
            controller.generateReport(surveyId);
            return true;
        } catch (InvalidSurveyException | NoFilesInsideDirectoryException | InvalidAnswerFileException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
