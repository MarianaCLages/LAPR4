package eapli.base.surveymanagement.application.questions;

import eapli.base.surveymanagement.domain.QuestionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class QuestionPrinterFactoryTest {


    /**
     * Method under test: {@link QuestionPrinterFactory#build()}
     */
    @Test
    void testBuild2() {

        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        questionPrinterFactory.setType(QuestionType.TEXT);
        Assertions.assertTrue(questionPrinterFactory.build() instanceof FreeTextQuestion);
    }

    /**
     * Method under test: {@link QuestionPrinterFactory#build()}
     */
    @Test
    void testBuild3() {
        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        questionPrinterFactory.setType(QuestionType.OPTION);
        Assertions.assertTrue(questionPrinterFactory.build() instanceof MultipleChoiceQuestion);

    }

    /**
     * Method under test: {@link QuestionPrinterFactory#build()}
     */
    @Test
    void testBuild4() {

        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        questionPrinterFactory.setType(QuestionType.INPUT);
        Assertions.assertTrue(questionPrinterFactory.build() instanceof SingleChoiceQuestion);
    }

    /**
     * Method under test: {@link QuestionPrinterFactory#build()}
     */
    @Test
    void testBuild5() {
        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        questionPrinterFactory.setType(QuestionType.NUMERIC);
        Assertions.assertTrue(questionPrinterFactory.build() instanceof NumericQuestion);
    }

    /**
     * Method under test: {@link QuestionPrinterFactory#build()}
     */
    @Test
    void testBuild6() {

        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        questionPrinterFactory.setType(QuestionType.SCALING);
        Assertions.assertTrue(questionPrinterFactory.build() instanceof ScalingQuestion);
    }

    /**
     * Method under test: {@link QuestionPrinterFactory#build()}
     */
    @Test
    void testBuild7() {

        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        questionPrinterFactory.setType(QuestionType.DECISION);
        Assertions.assertTrue(questionPrinterFactory.build() instanceof DecisionQuestion);
    }

    /**
     * Method under test: {@link QuestionPrinterFactory#build()}
     */
    @Test
    void testBuild8() {

        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        questionPrinterFactory.setType(QuestionType.SORT);
        Assertions.assertTrue(questionPrinterFactory.build() instanceof SortQuestion);
    }


    @Test
    void testConstructor() {
        QuestionPrinterFactory questionPrinterFactory = new QuestionPrinterFactory();
        Assertions.assertNotNull(questionPrinterFactory);
    }


    @Test
    void testConstructor2() {

        QuestionPrinterFactory actualQuestionPrinterFactory = new QuestionPrinterFactory();
        actualQuestionPrinterFactory.setOptions("Options");
        actualQuestionPrinterFactory.setQuestion("Question");
        actualQuestionPrinterFactory.setType(QuestionType.TEXT);
        QuestionPrinter printer = actualQuestionPrinterFactory.build();
        Assertions.assertEquals("Question", printer.print());

    }
}

