package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseName;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryRepository;

import java.util.Optional;
import java.util.function.Function;


public class InMemoryWarehouseRepository extends InMemoryRepository<Warehouse, Long> implements WarehouseRepository {

    static {
        InMemoryInitializer.init();
    }

    protected InMemoryWarehouseRepository(Function<? super Warehouse, ? extends Long> identityGenerator) {
        super(identityGenerator);
    }


    @Override
    public Optional<Warehouse> findByName(WarehouseName name) {
        return Optional.empty();
    }

    @Override
    public Optional<Warehouse> ofIdentity(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(Long entityId) {
        throw new UnsupportedOperationException("Not supported yet.");

    }
}
