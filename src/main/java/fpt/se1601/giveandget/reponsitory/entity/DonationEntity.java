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
    @ManyToOne
    @JoinColumn(name = "AREA_id")
    private AreaEntity area;
    @Column(name = "link_images")
    private String linkImages;
    @ManyToOne
    @JoinColumn(name = "TYPE_id")
    private DonationTypeEntity donationType;
    @Column(name = "description")
    private String description;
    @Column(name = "created_timestamp", updatable = false,insertable = false)
    private Timestamp createdTimestamp;
    @Column(name = "is_received")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean is_received;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donation")
    private Set<RelationshipEntity> relationshipEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donation")
    private Set<CommentEntity> commentEntities;

    public DonationEntity(DonationRequest donationRequest) {
        this.id = donationRequest.getId();
        this.name = donationRequest.getName();
        this.address = donationRequest.getAddress();
        this.area = donationRequest.getAreaEntity();
        this.linkImages = donationRequest.getLinkImages();
        this.donationType = donationRequest.getDonationTypeEntity();
        this.description = donationRequest.getDescription();
    }

    public DonationEntity(Integer id) {
        this.id = id;
    }
}
