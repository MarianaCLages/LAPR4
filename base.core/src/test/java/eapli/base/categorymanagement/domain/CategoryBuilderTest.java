package eapli.base.categorymanagement.domain;

import eapli.framework.general.domain.model.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryBuilderTest {

    private static final Description DESCRIPTION = Description.valueOf("This is a test Description");
    private static final AlphaNumericCode ALPHA_NUMERIC_CODE = AlphaNumericCode.valueOf("T0001");

    private Category buildCategory() {
        return new CategoryBuilder().withADescription(DESCRIPTION).coded(ALPHA_NUMERIC_CODE).build();
    }

    @Test
    public void ensureCanBuildCategoryWithDescriptionAndCode(){
        final Category subject = new CategoryBuilder().withADescription(DESCRIPTION).coded(ALPHA_NUMERIC_CODE).build();

        assertNotNull(subject);
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void ensureCannotBuildCategoryWithDescriptionNull() {
        new CategoryBuilder().withADescription((Description) null).coded(ALPHA_NUMERIC_CODE).build();
    }

    @org.junit.Test(expected = IllegalStateException.class)
    public void ensureCannotBuildCategoryWithCodeNull() {
        new CategoryBuilder().withADescription(DESCRIPTION).coded((AlphaNumericCode) null).build();
    }

}