package eapli.base.categorymanagement.domain;

import eapli.base.categorymanagement.dto.CategoryDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class Category implements AggregateRoot<Long>, DTOable<CategoryDTO>, Representationable {

    private static final long serialVersionUID = 696969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Version
    private Long version;

    @AttributeOverride(name = "value", column = @Column(name = "description"))
    @Column(nullable = false)
    private Description description;

    @Column(nullable = false)
    private AlphaNumericCode code;

    public Category(final Description description, final AlphaNumericCode code) {
        Preconditions.noneNull(description, code);

        this.code = code;
        this.description = description;

    }

    protected Category() {
        // for ORM only.
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Category)) {
            return false;
        }

        final Category that = (Category) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && code.equals(that.code)
                && description.equals(that.description);
    }

    @Override
    public Long identity() {
        return this.categoryId;
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        builder.startObject("Category").withProperty("description", description).withProperty("code", String.valueOf(code));

        return builder.build();
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public CategoryDTO toDTO() {
        return new CategoryDTO(code.toString(), description.toString());
    }

    private void changeDescription(final Description description){
        if(description == null) {
            throw new IllegalArgumentException();
        }
        this.description = description;
    }

    private void changeAlphaNumericCode(final AlphaNumericCode code){
        if(code == null) {
            throw new IllegalArgumentException();
        }
        this.code = code;
    }

    public void update(final AlphaNumericCode code,final Description description) {
        Preconditions.noneNull(code,description);

        changeAlphaNumericCode(code);
        changeDescription(description);
    }

}