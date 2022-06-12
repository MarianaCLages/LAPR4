package eapli.base.persistence.impl.jpa;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVDescription;
import eapli.base.agvmanagement.domain.AGVModel;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.domain.AGVDock;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class JpaAGVRepository extends BasepaRepositoryBase<AGV, Long, Long> implements AGVRepository {
    public JpaAGVRepository() {
        super("AGVId");

    }

    @Override
    public AGV findByID(long id) {
        TypedQuery<AGV> q = createQuery("SELECT e FROM AGV e where e.agvId = :m", AGV.class);

        q.setParameter("m", id);

        return q.getSingleResult();

    }

    @Override
    public AGV findByDock(AGVDock dock) {
        AGV agv = (AGV) entityManager().createQuery("SELECT a FROM AGV a WHERE a.dock = :dock", AGV.class);
        return agv;
    }

    @Override
    public List<AGV> findFreeAGVS() {

        List<AGV> agvList = new ArrayList<>();
        AGV agv = null;

        TypedQuery<AGV> q = createQuery("SELECT e FROM AGV e",AGV.class);

        Iterator<AGV> agvIterator = q.getResultList().iterator();

        while(agvIterator.hasNext()){
            agv = agvIterator.next();

            if(agv.agvStatus().equals(AGVStatus.AVAILABLE)){agvList.add(agv);}
        }

        return agvList;
    }

    @Override
    public void updateAGV(AGV agv) {
        entityManager().getTransaction().begin();
        entityManager().merge(agv);
        entityManager().getTransaction().commit();
    }

    @Override
    public List<AGV> findAllAGVS() {
        final TypedQuery<AGV> q = createQuery("select p from AGV p",
                AGV.class);

        return q.getResultList();
    }

    @Override
    public AGV findByModelAndDescription(AGVModel model, AGVDescription description) {
        TypedQuery<AGV> q = createQuery("SELECT e FROM AGV e where e.model = :m and e.shortDescription = :s", AGV.class);

        q.setParameter("m", model);
        q.setParameter("s", description);

        return q.getSingleResult();
    }


}
