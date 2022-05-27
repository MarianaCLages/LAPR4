package eapli.base.agvmanagement.application;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.AGVDescription;
import eapli.base.agvmanagement.domain.AGVModel;
import eapli.base.agvmanagement.dto.AGVDto;
import eapli.base.agvmanagement.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.ApplicationService;

import java.util.ArrayList;
import java.util.List;

@ApplicationService
public class ViewAllAgvsService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agvRepository();

    public List<AGVDto> viewAll() {

        List<AGV> list = agvRepository.findFreeAGVS();
        List<AGVDto> agvDtoList = new ArrayList<>();

        for (AGV a : list) {
            if (a.getClientOrder() == null) {
                agvDtoList.add(a.toDTO());
            }

        }

        return agvDtoList;

    }

    public AGV getAgvByModelAndDescription(String model, String description) {
        return agvRepository.findByModelAndDescription(AGVModel.valueOf(model), AGVDescription.valueOf(description));
    }

    public void saveAgv(AGV agv) {
        agvRepository.save(agv);
    }

    public AGV getAgvById(long id) {
        return agvRepository.findByID(id);
    }

}