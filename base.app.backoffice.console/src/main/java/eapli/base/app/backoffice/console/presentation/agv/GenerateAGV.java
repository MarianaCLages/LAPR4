package eapli.base.app.backoffice.console.presentation.agv;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVBuilder;
import eapli.base.agvmanagement.domain.AGVStatus;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

public class GenerateAGV {

    private AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();


    public boolean createAGVs(){

        AGVBuilder agvBuilder = new AGVBuilder();

        AGV agv1 = agvBuilder.description("aaaaaaa").autonomy(10).identifier("11223345").status(AGVStatus.AVAILABLE).model("MODEL60").build();
        AGV agv2 = agvBuilder.description("aaaaaaa").autonomy(10).identifier("1122AA45").status(AGVStatus.AVAILABLE).model("MODEL60").build();
        AGV agv3 = agvBuilder.description("aaaaaaa").autonomy(10).identifier("11BB3345").status(AGVStatus.AVAILABLE).model("MODEL60").build();

        agvRepository.save(agv1);
        agvRepository.save(agv2);
        agvRepository.save(agv3);

        return true;
    }
}
