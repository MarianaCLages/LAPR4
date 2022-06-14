package eapli.base.surveymanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class SectionDTO {
    private final List<QuestionDTO> questions = new ArrayList<>();
    private final String id;
    private final String title;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id).append("\n");
        sb.append("title: ").append(title).append("\n");
        sb.append("questions: ").append("\n");
        for (QuestionDTO question : questions) {
            System.out.println(question.toString());
            sb.append(question.toString());
        }
        return sb.toString();
    }

    private final String description;

    public SectionDTO(String id, String description, List<QuestionDTO> questions, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.questions.addAll(questions);
    }

    public List<QuestionDTO> questions() {
        return questions;
    }

    public String id() {
        return id;
    }

    public String description() {
        return description;
    }
}