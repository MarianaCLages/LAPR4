package eapli.base.surveymanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDTO {

    private final List<SectionDTO> sections = new ArrayList<>();
    private final String id;
    private final String title;
    private final String welcomingMessage;
    private final String goodbyeMessage;

    public List<SectionDTO> sections() {
        return sections;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id).append("\n");
        sb.append("title: ").append(title).append("\n");
        sb.append("welcomingMessage: ").append(welcomingMessage).append("\n");
        sb.append("sections: ").append("\n");
        for (SectionDTO section : sections) {
            sb.append(section.toString());
        }
        sb.append("goodbyeMessage: ").append(goodbyeMessage).append("\n");
        return sb.toString();
    }

    public String title() {
        return title;
    }

    public String id() {
        return id;
    }

    public String welcomingMessage() {
        return welcomingMessage;
    }

    public String goodbyeMessage() {
        return goodbyeMessage;
    }

    public QuestionnaireDTO(String id, String welcomingMessage, String goodbyeMessage, List<SectionDTO> sections, String title) {
        this.id = id;
        this.welcomingMessage = welcomingMessage;
        this.goodbyeMessage = goodbyeMessage;
        this.title = title;
        this.sections.addAll(sections);
    }
}
