package eapli.base.persistence.impl.jpa;


import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseName;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;

import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaWarehouseRepository extends BasepaRepositoryBase<Warehouse, WarehouseName, WarehouseName> implements WarehouseRepository {


    public JpaWarehouseRepository() {
        super("warehouseId");
    }

    @Override//TODO: check if this is correct
    public Optional<Warehouse> findByName(WarehouseName name) {
        final TypedQuery<Warehouse> findByName = createQuery("Select e from Warehouse e where e.name = :name", Warehouse.class);
        findByName.setParameter("name", name);
        return Optional.ofNullable(findByName.getSingleResult());
    }
}
