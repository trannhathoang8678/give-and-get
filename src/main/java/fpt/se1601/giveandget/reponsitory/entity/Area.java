package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`AREA`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "area")
    private Set<Donation> donations;
}
