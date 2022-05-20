package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.domain.Shelf;
import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseName;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
        return matchOne(warehouse -> warehouse.name().equals(name.toString()));
    }

    @Override
    public boolean isImported() {
        return matchOne(warehouse -> warehouse.identity() == 1).isPresent();
    }

    @Override
    public void removeImported() {
        for (Iterator<Warehouse> it = iterator(); it.hasNext(); ) {
            Warehouse warehouse = it.next();
            //delete
            it.remove();
        }
    }

    @Override
    public Warehouse findWarehouse() {
        return matchOne(warehouse -> warehouse.identity() == 1).get();
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
        Warehouse w = matchOne(warehouse -> Objects.equals(warehouse.identity(), id)).get();
        List<AGVDock> agvDocks = w.agvDocks();
        return agvDocks.stream().filter(agvDock -> Objects.equals(agvDock.identity(), id)).findFirst().get();
    }

    @Override
    public Optional<Warehouse> ofIdentity(Long id) {
        return matchOne(warehouse -> Objects.equals(warehouse.identity(), id));
    }

    @Override
    public void deleteOfIdentity(Long entityId) {
        matchOne(warehouse -> Objects.equals(warehouse.identity(), entityId)).ifPresent(this::delete);
    }
}
