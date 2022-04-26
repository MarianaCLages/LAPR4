package eapli.base.persistence.impl.jpa;


import eapli.base.warehousemanagement.domain.Warehouse;
import eapli.base.warehousemanagement.domain.WarehouseName;
import eapli.base.warehousemanagement.repositories.WarehouseRepository;
import eapli.framework.domain.repositories.ConcurrencyException;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class JpaWarehouseRepository extends BasepaRepositoryBase<Warehouse, WarehouseName, WarehouseName> implements WarehouseRepository {


    public JpaWarehouseRepository() {
        super("warehouseId");
    }

 /*   @Override
    public <S extends Warehouse> S save(S entity) {
        Warehouse entity1 = entity;
        try {
             this.entityManager().merge(entity1);
             this.entityManager().merge(entity1);
        } catch (OptimisticLockException var3) {
            throw new ConcurrencyException(var3);
        } catch (PersistenceException var4) {
            throw new PersistenceException(var4);
    }*/

    @Override//TODO: check if this is correct
    public Optional<Warehouse> findByName(WarehouseName name) {
        final TypedQuery<Warehouse> findByName = createQuery("Select e from Warehouse e where e.name = :name", Warehouse.class);
        findByName.setParameter("name", name);
        return Optional.ofNullable(findByName.getSingleResult());
    }

    @Override
    public Optional<Warehouse> ofIdentity(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(Long entityId) {

    }
}
