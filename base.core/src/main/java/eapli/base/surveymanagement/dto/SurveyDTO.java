package eapli.base.surveymanagement.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class SurveyDTO {

    private String surveyCode;
    private String description;
    private String period;

    public SurveyDTO(final String surveyCode, final String description, String period) {
        this.surveyCode = surveyCode;
        this.description = description;
        this.period = period;
    }

    @Override
    public String toString() {
        return "Code: " + surveyCode + '\n' +
                "Description: " + description + '\n' +
                "Period: " + period + "days" + '\n';
    }

    public String getSurveyCode() {
        return surveyCode;
    }

    public String getDescription() {
        return description;
    }

    public String getPeriod() {
        return period;
    }
}
