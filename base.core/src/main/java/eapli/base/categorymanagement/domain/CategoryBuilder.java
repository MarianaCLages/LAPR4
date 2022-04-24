package eapli.base.categorymanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;

public class CategoryBuilder implements DomainFactory<Category> {

    private Category theCategory;

    private Description description;
    private AlphaNumericCode code;

    public CategoryBuilder withADescription(final Description description) {
        this.description = description;
        return this;
    }

    public CategoryBuilder withADescription(final String description) {
        return withADescription(Description.valueOf(description));
    }

    public CategoryBuilder coded(final AlphaNumericCode code) {
        this.code = code;
        return this;
    }

    public CategoryBuilder coded(final String code) {
        return coded(AlphaNumericCode.valueOf(code));
    }

    private Category buildOrThrow() {
        if (theCategory != null) {
            return theCategory;
        } else if (description != null && code != null) {
            theCategory = new Category(description, code);
            return theCategory;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Category build() {
        final Category ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built dish.
        theCategory = null;
        return ret;
    }

}
