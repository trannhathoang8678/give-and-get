package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "`RELATIONSHIP`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DONATION_id")
    private DonationEntity donation;

    public RelationshipEntity(UserEntity userEntity, DonationEntity donationEntity) {
        this.user = userEntity;
        this.donation = donationEntity;
    }

    @Column(name = "is_donor")
    private short isDonor;

    public RelationshipEntity(UserEntity userEntity, DonationEntity donationEntity, short isDonor) {
        this.user = userEntity;
        this.donation = donationEntity;
        this.isDonor = isDonor;
    }
}
