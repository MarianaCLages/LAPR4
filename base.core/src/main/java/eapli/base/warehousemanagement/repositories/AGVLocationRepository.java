package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.AGVLocation;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVLocationRepository extends DomainRepository<Long, AGVLocation> {

    AGVLocation findByAGVID(int agvID);

}
