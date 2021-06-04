package fpt.se1601.giveandget.reponsitory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "`RELATIONSHIP`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "USER_id")
    private Integer userID;
    @Column(name = "DONARION_id")
    private Integer donationID;
    @Column(name = "is_donor")
    private short isDonor;
}
