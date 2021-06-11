package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "`RELATIONSHIP`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_id")
    private DonationEntity donationEntity;
    @Column(name = "is_donor")
    private short isDonor;
}
