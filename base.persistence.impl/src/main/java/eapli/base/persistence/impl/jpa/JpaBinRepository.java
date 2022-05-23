package eapli.base.persistence.impl.jpa;

import eapli.base.binmanagement.domain.Bin;
import eapli.base.binmanagement.repositories.BinRepository;

import javax.persistence.TypedQuery;

public class JpaBinRepository extends BasepaRepositoryBase<Bin, Long, Long> implements BinRepository {

    public JpaBinRepository() {
        super("id");
    }

    @Override
    public Bin findById(long id) {
        final TypedQuery<Bin> q = createQuery("SELECT b FROM Bin b WHERE b.binId = :m",
                Bin.class);

        q.setParameter("m", id);
        return q.getSingleResult();
    }

    @Override
    public void updateBin(Bin b) {
        entityManager().getTransaction().begin();
        entityManager().merge(b);
        entityManager().getTransaction().commit();
    }
}
