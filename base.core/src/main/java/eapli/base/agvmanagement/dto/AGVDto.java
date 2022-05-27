package eapli.base.agvmanagement.dto;

import eapli.framework.representations.dto.DTO;

@DTO
public class AGVDto {

    private String agvAutonomy;
    private String agvDescription;
    private String agvModel;
    private String agvStatus;
    private int agvDock;
    private long capacity;
    private long id;

    public AGVDto(final String agvAutonomy, final String agvDescription, final String agvModel, final String agvStatus, final long id, final long capacity) {

        this.agvAutonomy = agvAutonomy;
        this.agvDescription = agvDescription;
        this.agvModel = agvModel;
        this.agvStatus = agvStatus;
        this.id = id;
        this.agvDock = 1;
        this.capacity = capacity;
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

    public String getAgvDescription() {
        return agvDescription;
    }

    public String getAgvModel() {
        return agvModel;
    }

    public long getId() {
        return id;
    }

    public long getCapacity() {
        return capacity;
    }
}
