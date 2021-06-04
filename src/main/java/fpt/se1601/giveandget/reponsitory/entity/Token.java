package fpt.se1601.giveandget.reponsitory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "`TOKEN`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "token")
    private String token;
    @Column(name = "created_timestamp")
    private Timestamp createdTimestamp;
}
