package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseName;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryRepository;

import java.util.Optional;
import java.util.function.Function;


public class InMemoryWarehouseRepository extends InMemoryRepository<Warehouse, WarehouseName> implements WarehouseRepository {

    static {
        InMemoryInitializer.init();
    }

    protected InMemoryWarehouseRepository(Function<? super Warehouse, ? extends WarehouseName> identityGenerator) {
        super(identityGenerator);
    }


    @Override
    public Optional<Warehouse> ofIdentity(WarehouseName id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(WarehouseName entityId) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public Optional<Warehouse> findByName(WarehouseName name) {
        return Optional.empty(); //Not supported yet
    }
}
