package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`AREA`")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "area")
    private Set<DonationEntity> donationEntities;

    public AreaEntity(String name) {
        this.name = name;
    }

    public AreaEntity(Integer id) {
        this.id = id;
    }
}
