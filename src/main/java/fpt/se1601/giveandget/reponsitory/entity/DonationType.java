package fpt.se1601.giveandget.reponsitory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`DONATION_TYPE`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "donationType")
    private Set<Donation> donations;
}