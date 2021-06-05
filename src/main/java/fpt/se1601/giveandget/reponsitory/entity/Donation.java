package fpt.se1601.giveandget.reponsitory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "`DONATION`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "areaID")
    private Area area;
    @Column(name = "link_images")
    private String linkImages;
    @ManyToOne
    @JoinColumn(name = "typeID")
    private DonationType donationType;
    @Column(name = "description")
    private String description;
    @Column(name = "created_timestamp")
    private Timestamp timestamp;
}
