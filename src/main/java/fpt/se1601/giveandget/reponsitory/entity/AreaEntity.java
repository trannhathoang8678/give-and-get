package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "`AREA`")
@Getter
@Setter
@Builder
@NoArgsConstructor

public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    public AreaEntity(String name) {
        this.name = name;
    }

    public AreaEntity(Integer id) {
        this.id = id;
    }

    public AreaEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
