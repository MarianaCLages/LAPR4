package eapli.base.persistence.impl.jpa;

import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.AGVLocation;
import eapli.base.warehousemanagement.repositories.AGVLocationRepository;

import javax.persistence.TypedQuery;

public class JpaAGVLocationRepository extends BasepaRepositoryBase<AGVLocation, Long, Long> implements AGVLocationRepository {

    public JpaAGVLocationRepository() {
        super("id");
    }

    @Override
    public AGVLocation findByAGVID(int agvID) {
        final TypedQuery<AGVLocation> q = createQuery("SELECT e FROM AGVLocation e WHERE  e.agvID = :m",
                AGVLocation.class);

        q.setParameter("m", agvID);
        return q.getSingleResult();
    }
}

