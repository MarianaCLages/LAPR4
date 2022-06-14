package eapli.base.surveymanagement.application;

import java.io.FileWriter;
import java.io.IOException;

public class AnswersTextExporter {
    private FileWriter stream;

    public void beginExport(String fileName) {
        try {
            stream = new FileWriter(fileName);
        } catch (IOException e) {
            throw new IllegalStateException("Could not create file " + fileName);
        }
    }

    public void addAnswer(String answer, String question, String type, String options) throws IOException {
        if (stream == null) {
            throw new IllegalStateException("File not initialized, please call initailize the export first");
        }

        stream.append(type);
        stream.append(";");
        stream.append(question);
        stream.append(";");
        stream.append(options);
        stream.append(System.lineSeparator());
        stream.append(answer);
        stream.append(System.lineSeparator());

    }

    public void endExport() throws IOException {
        if (stream == null) {
            throw new IllegalStateException("File not initialized, please call initailize the export first");
        }
        stream.close();
    }
}
