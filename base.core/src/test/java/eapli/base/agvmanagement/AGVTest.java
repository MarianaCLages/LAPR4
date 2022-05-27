package eapli.base.agvmanagement;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVCapacity;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.warehousemanagement.domain.AGVDock;
import eapli.base.warehousemanagement.domain.Accessibility;
import eapli.base.warehousemanagement.domain.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AGVTest {
    @Test
    void ensureAGVCanBeCreated() {
        //dock for the agv
        AGVDock dock = new AGVDock("Dock1", new Location(1, 1), new Location(2, 2), new Location(3, 3), Accessibility.LENGHT_PLUS);
        //agv
        AGV agv = new AGV("D452FDD1", 5000, "First AGV", "AutomatedSolutions", AGVStatus.AVAILABLE, dock, new AGVCapacity(10));
        Assertions.assertEquals("D452FDD1", agv.identifier().toString());
    }
}