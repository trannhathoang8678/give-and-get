package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "`DONATION`")
@Getter
@Setter
@Builder
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
    @OneToMany(mappedBy = "donation", fetch = FetchType.LAZY)
    private Set<Relationship> relationships;
    @OneToMany(mappedBy = "donation", fetch = FetchType.LAZY)
    private Set<Comment> commentss;
}
