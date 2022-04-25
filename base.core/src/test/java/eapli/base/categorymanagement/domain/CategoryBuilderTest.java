package eapli.base.categorymanagement.domain;


import eapli.framework.general.domain.model.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void ensureCannotBuildCategoryWithDescriptionNull() {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryBuilder().withADescription(Description.valueOf("")).coded(ALPHA_NUMERIC_CODE).build());
        assertEquals("Description should neither be null nor empty",e.getMessage());

    }

    @Test
    public void ensureCannotBuildCategoryWithCodeNull() {
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> new CategoryBuilder().withADescription(DESCRIPTION).coded(AlphaNumericCode.valueOf("")).build());
        assertEquals("AlphaNumeric code should neither be null nor empty",e.getMessage());
    }

}