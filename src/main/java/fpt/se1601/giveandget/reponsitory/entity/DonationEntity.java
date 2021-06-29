package fpt.se1601.giveandget.reponsitory.entity;

import fpt.se1601.giveandget.controller.request.DonationRequest;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "`DONATION`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "AREA_id")
    private int areaId;
    @Column(name = "link_images")
    private String linkImages;
    @Column(name = "TYPE_id")
    private int typeId;
    @Column(name = "description")
    private String description;
    @Column(name = "created_timestamp", updatable = false, insertable = false)
    private Timestamp createdTimestamp;
    @Column(name = "is_received",insertable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isReceived;

    public DonationEntity(DonationRequest donationRequest) {
        this.id = donationRequest.getId();
        this.name = donationRequest.getName();
        this.address = donationRequest.getAddress();
        this.areaId = donationRequest.getAreaId();
        this.linkImages = donationRequest.getLinkImages();
        this.typeId = donationRequest.getTypeId();
        this.description = donationRequest.getDescription();
    }

    public DonationEntity(Integer id, String name, String address, int areaId, String linkImages, int typeId, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.areaId = areaId;
        this.linkImages = linkImages;
        this.typeId = typeId;
        this.description = description;
    }

    public DonationEntity(Integer id) {
        this.id = id;
    }
}
