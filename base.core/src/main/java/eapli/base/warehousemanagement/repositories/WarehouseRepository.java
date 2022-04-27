package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseName;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface WarehouseRepository extends DomainRepository<Long, Warehouse> {

    Optional<Warehouse> findByName(WarehouseName name);

    boolean isImported();

    int removeImported();
}
