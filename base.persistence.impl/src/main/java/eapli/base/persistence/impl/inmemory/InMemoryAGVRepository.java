package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVDescription;
import eapli.base.agvmanagement.domain.AGVModel;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.domain.AGVDock;

import java.util.List;
import java.util.Optional;

public class InMemoryAGVRepository implements AGVRepository {
    @Override
    public AGV findByID(long id) {
        return null;
    }

    @Override
    public AGV findByDock(AGVDock dock) {
        return null;
    }

    @Override
    public List<AGV> findFreeAGVS() {return null;}

    @Override
    public void updateAGV(AGV agv) {

    }

    @Override
    public List<AGV> findAllAGVS() {
        return null;
    }

    @Override
    public AGV findByModelAndDescription(AGVModel model, AGVDescription description) {
        return null;
    }

    @Override
    public <S extends AGV> S save(S entity) {
        return null;
    }

    @Override
    public Iterable<AGV> findAll() {
        return null;
    }

    @Override
    public Optional<AGV> ofIdentity(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(AGV entity) {

    }

    @Override
    public void deleteOfIdentity(Long entityId) {

    }

    @Override
    public long count() {
        return 0;
    }
}
