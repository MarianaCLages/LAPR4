package eapli.base.surveymanagement.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AnswersTextExporter {
    private FileWriter stream;

    public void beginExport(String fileName, String directory) {
        //create the directory if it doesn't exist

        File answersFile = new File(fileName);
        try {
            File directoryFile = new File(directory);
            if (!directoryFile.exists()) {

                Files.createDirectories(Paths.get(directory));

                if (!answersFile.createNewFile()) {
                    throw new IOException("File already exists"); //TODO: throw exception made for this
                }
            }
            stream = new FileWriter(fileName);

        } catch (IOException e) {
            throw new IllegalStateException("Could not create file " + fileName + "\n" + e.getMessage()); //TODO: throw exception made for this
        }

    }

    public void addAnswer(String answer, String question, String type, String options) throws IOException {
        if (stream == null) {
            throw new IllegalStateException("File not initialized, please call initailize the export first");
        }

        stream.append(type);
        stream.append(";");
        stream.append(question);
        if (!options.isEmpty()) {
            stream.append(";");
            stream.append(optionsFormater(options));
        }
        stream.append(System.lineSeparator());
        stream.append(answerFormater(answer, type));
        stream.append(System.lineSeparator());

    }

    public void endExport() throws IOException {
        if (stream == null) {
            throw new IllegalStateException("File not initialized, please call initailize the export first");
        }
        stream.close();
    }


    protected String optionsFormater(String options) {
        //takes of the line breaks
        String optionsFormated = options.replace(System.lineSeparator(), " ");
        optionsFormated = optionsFormated.replace(" ", ";");

        //removes the last ; if there is one
        if (optionsFormated.endsWith(";")) {
            optionsFormated = optionsFormated.substring(0, optionsFormated.length() - 1);
        }

        return optionsFormated;
    }

    protected String answerFormater(String answer, String type) {
        //takes of the line breaks
        String answerFormated = answer.replace("\n", " ");
        if ("MULTIPLECHOICEINPUT".equals(type)) {
            answerFormated = answerFormated.replace(" ", ",");
        } else {
            answerFormated = answerFormated.replace(" ", ";");
        }

        return answerFormated;
    }
}
