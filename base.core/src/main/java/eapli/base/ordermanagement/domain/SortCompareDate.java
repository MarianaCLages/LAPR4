package eapli.base.ordermanagement.domain;

import java.util.Comparator;

public class SortCompareDate implements Comparator<ClientOrder> {
    @Override
    public int compare(ClientOrder o1, ClientOrder o2) {

        //Compare Date
        return o1.orderDate().date().compareTo(o2.orderDate().date());
    }
}
