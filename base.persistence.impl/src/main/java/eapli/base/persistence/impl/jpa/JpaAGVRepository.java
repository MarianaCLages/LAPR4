package eapli.base.persistence.impl.jpa;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.warehousemanagement.domain.AGVDock;

import javax.persistence.EntityManager;
import java.util.Optional;

public class JpaAGVRepository extends BasepaRepositoryBase<AGV, Long, Long> implements AGVRepository {
    public JpaAGVRepository() {
        super("AGVId");

    }

    @Override
    public AGV findByID(String id) {
        return entityManager().find(AGV.class, id);

    }

    @Override
    public AGV findByDock(AGVDock dock) {
        AGV agv = (AGV) entityManager().createQuery("SELECT a FROM AGV a WHERE a.dock = :dock", AGV.class);
        return agv;
    }
}
