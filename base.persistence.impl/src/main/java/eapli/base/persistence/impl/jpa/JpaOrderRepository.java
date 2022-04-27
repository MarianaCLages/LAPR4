package eapli.base.persistence.impl.jpa;

import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.ordermanagement.repositories.OrderRepository;

public class JpaOrderRepository extends BasepaRepositoryBase<ClientOrder,Long,Long> implements OrderRepository {


    public JpaOrderRepository(){
        super("id");
    }


}
