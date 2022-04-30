package eapli.base.agvmanagement.application;


import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.warehousemanagement.domain.AGVDock;

public class CreateAGVController {

    private CreateAGVService createAGVService = new CreateAGVService();
    private SearchAllAGVDocksService searchAllAGVDocksService = new SearchAllAGVDocksService();
    private SearchAGVDockService searchAGVDockService = new SearchAGVDockService();

    public boolean createAGV(String agvIdentifier, int agvAutonomy,
                             String agvDescription, String agvModel,
                             AGVStatus agvStatus, AGVDock agvDock) {
        return createAGVService.createAGVService(agvIdentifier, agvAutonomy, agvDescription, agvModel, agvStatus, agvDock);
    }

    public Iterable<AGVDock> searchAllAGVDock(){
        return searchAllAGVDocksService.searchAllAGVDocksService();
    }

    public AGVDock searchAGVDock(Integer id){
        return searchAGVDockService.searchAGVDockByIdSerivice(id);
    }
}
