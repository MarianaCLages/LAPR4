package eapli.base.categorymanagement.repositories;

import eapli.base.categorymanagement.domain.AlphaNumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Description;

public interface CategoryRepository extends DomainRepository<Long, Category> {

    Category findByCode(AlphaNumericCode code);

    Category findById(long id);

    Category findByDescription(Description description);

}
