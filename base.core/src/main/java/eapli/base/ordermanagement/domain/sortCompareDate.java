package eapli.base.ordermanagement.domain;

import java.util.Comparator;

public class sortCompareDate implements Comparator<ClientOrder> {
    @Override
    public int compare(ClientOrder o1, ClientOrder o2) {

        return o1.orderDate().date().compareTo(o2.orderDate().date());
    }
}
