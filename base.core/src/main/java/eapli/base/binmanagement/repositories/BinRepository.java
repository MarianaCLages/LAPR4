package eapli.base.binmanagement.repositories;

import eapli.base.binmanagement.domain.Bin;
import eapli.framework.domain.repositories.DomainRepository;

public interface BinRepository extends DomainRepository<Long, Bin> {

    Bin findById(long id);
}
