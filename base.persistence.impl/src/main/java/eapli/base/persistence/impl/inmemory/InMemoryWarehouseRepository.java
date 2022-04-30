package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.domain.Shelf;
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
    public boolean isImported() {
        return false;
    }

    @Override
    public void removeImported() {
    }

    @Override
    public Warehouse findWarehouse() {
        return null;
    }

    @Override
    public void updateShelf(Shelf s) {

    }

    @Override
    public Iterable<AGVDock> findAllAGVDock() {
        return null;
    }

    @Override
    public AGVDock searchAGVDockById(Integer id) {
        return null;
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
