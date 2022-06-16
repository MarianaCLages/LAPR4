package eapli.base.warehousemanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
public class AGVLocation implements ValueObject, AggregateRoot<Long> {

    private static final long serialVersionUID = 123123123123123123L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long positionID;

    @Column(nullable = false)
    private int xPos;

    @Column(nullable = false)
    private int yPos;

    @Column(nullable = false)
    private int agvID;

    protected AGVLocation() {
        //FOR ORM PURPOSES
    }

    public AGVLocation(int xPos, int yPos, int agvID) {
        Preconditions.isPositive(xPos, "X position must be valid!");
        Preconditions.isPositive(yPos, "Y position must be valid!");
        Preconditions.isPositive(agvID, "AGVId must be valid!");

        this.xPos = xPos;
        this.yPos = yPos;
        this.agvID = agvID;

    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof Product)) {
            return false;
        }

        final AGVLocation that = (AGVLocation) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && this.xPos == that.xPos && this.yPos == that.yPos && this.agvID == that.agvID;
    }

    @Override
    public Long identity() {
        return positionID;
    }

    public void updatePosition(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
