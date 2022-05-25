package eapli.base.agvmanagement.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class AGVDto {

    private String agvAutonomy;
    private String agvDescription;
    private String agvModel;
    private String agvStatus;
    private int agvDock;

    public AGVDto(final String agvAutonomy, final String agvDescription, final String agvModel, final String agvStatus, final int agvDock) {

        this.agvAutonomy = agvAutonomy;
        this.agvDescription = agvDescription;
        this.agvModel = agvModel;
        this.agvStatus = agvStatus;
        this.agvDock = agvDock;
    }

    @Override
    public String toString() {
        return "AGV ->[" +
                "AGVAutonomy : " + agvAutonomy + '\n' +
                "AGVDescription : " + agvDescription + '\n' +
                "AGVModel : " + agvModel + '\n' +
                "AGVStatus : " + agvStatus + '\n' +
                "AGVDock : " + agvDock +
                ']';
    }
}
