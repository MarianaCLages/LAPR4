package eapli.base.agvmanagement.domain;

import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.ordermanagement.domain.ClientOrder;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AGV implements AggregateRoot<Long>, DTOable<AGVDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long agvId;

    @Embedded
    private AGVIdentifier identifier;
    @Embedded
    private AGVAutonomy autonomy;
    @Embedded
    private AGVDescription shortDescription;
    @Embedded
    private AGVModel model;
    private AGVStatus status;
    @OneToOne
    private AGVDock dock;
    @OneToOne
    private ClientOrder clientOrder;
    @OneToMany
    private List<ClientOrder> clientOrderList;

    private AGVCapacity agvCapacity;


    public AGV(String identifier, int autonomy, String shortDescription, String model, AGVStatus status, AGVDock dock, AGVCapacity agvCapacity) {
        this.identifier = AGVIdentifier.valueOf(identifier);
        this.autonomy = AGVAutonomy.valueOf(autonomy);
        this.shortDescription = AGVDescription.valueOf(shortDescription);
        this.model = AGVModel.valueOf(model);
        this.status = status;
        this.dock = dock;
        this.clientOrder = null;
        this.agvCapacity = agvCapacity;
        this.clientOrderList = new ArrayList<>();
    }

    protected AGV() {
        // for ORM only
    }


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return this.agvId;
    }

    public AGVStatus agvStatus() {
        return this.status;
    }

    public AGVIdentifier identifier() {
        return this.identifier;
    }

    public void changeClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public void changeStatus(AGVStatus agvStatus) {
        this.status = agvStatus;
    }

    public void changeAutonomy(final AGVAutonomy autonomy) {
        if (autonomy == null) {
            throw new IllegalArgumentException();
        }
        this.autonomy = autonomy;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void addAlreadyProcessedOrdersToList(ClientOrder clientOrder) {
        clientOrderList.add(clientOrder);
    }

    @Override
    public AGVDto toDTO() {
        return new AGVDto(autonomy.toString(), shortDescription.toString(), model.toString(), status.toString(), identity(), agvCapacity.returnValue());
    }

    public String AllProcessedOrders() {
       String s = new String();

        for (ClientOrder clientOrder : clientOrderList) {
            s = s + clientOrder.toString();
        }
        return s;
    }

}
