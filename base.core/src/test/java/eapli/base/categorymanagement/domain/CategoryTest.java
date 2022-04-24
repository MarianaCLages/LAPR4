package eapli.base.categorymanagement.domain;

import eapli.framework.general.domain.model.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private static final Description DESCRIPTION_NAME = Description.valueOf("A valid description for tests!");
    private static final AlphaNumericCode ALPHA_NUMERIC_CODE = AlphaNumericCode.valueOf("C0001");

    private Category buildCategory() {
        return new CategoryBuilder().withADescription(DESCRIPTION_NAME).coded(ALPHA_NUMERIC_CODE).build();
    }

    @Test
    public void ensureCategoryWithDescriptionAndCode() {
        new Category(DESCRIPTION_NAME, ALPHA_NUMERIC_CODE);
        assertTrue(true);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveDescription() {
        new Category(null, ALPHA_NUMERIC_CODE);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveCode() {
        new Category(DESCRIPTION_NAME, null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeCodeToNull() {
        final Category subject = buildCategory();

        final AlphaNumericCode newInfo = null;

        subject.update(newInfo, DESCRIPTION_NAME);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureCannotChangeDescriptionToNull() {
        final Category subject = buildCategory();

        final Description newInfo = null;

        subject.update(ALPHA_NUMERIC_CODE, newInfo);
    }

    @Test
    public void ensureCanChangeDescription() {
        final Category subject = buildCategory();

        final Description newInfo = Description.valueOf("Another valid description!");

        subject.update(ALPHA_NUMERIC_CODE, newInfo);
    }

    @Test
    public void ensureCanChangeCode() {
        final Category subject = buildCategory();

        final AlphaNumericCode newInfo = AlphaNumericCode.valueOf("C0003");

        subject.update(newInfo, DESCRIPTION_NAME);
    }

}