package fpt.se1601.giveandget.controller.request;

import fpt.se1601.giveandget.reponsitory.entity.AreaEntity;
import fpt.se1601.giveandget.reponsitory.entity.DonationTypeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationRequest {
    private int id;
    private String name;
    private String address;
    private int areaId;
    private String linkImages;
    private int typeId;
    private String description;
}
