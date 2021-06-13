package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`DONATION_TYPE`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "donationTypeEntity")
    private Set<DonationEntity> donationEntities;
}
