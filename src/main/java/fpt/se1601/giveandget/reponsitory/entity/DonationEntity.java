package fpt.se1601.giveandget.reponsitory.entity;

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
    private AreaEntity areaEntity;
    @Column(name = "link_images")
    private String linkImages;
    @ManyToOne
    @JoinColumn(name = "TYPE_id")
    private DonationTypeEntity donationTypeEntity;
    @Column(name = "description")
    private String description;
    @Column(name = "created_timestamp")
    private Timestamp timestamp;
    @Column(name = "is_received")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean is_received;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donationEntity")
    private Set<RelationshipEntity> relationshipEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donationEntity")
    private Set<CommentEntity> commentEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "donationEntity")
    private Set<InterestedDonationEntity> interestedDonationEntities;
}
