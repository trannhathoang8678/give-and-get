package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`USER`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String phone;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String avatar;
    @Column
    private short sex;//ISO 5218
    @Column
    private short age;
    @Column(name = "link_contact_info")
    private String linkContactInfo;
    @Column
    private String role;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "TOKEN_id")
    private TokenEntity tokenEntity;


    public UserEntity(String email, TokenEntity tokenEntity) {
        this.email = email;
        this.tokenEntity = tokenEntity;
    }

    public UserEntity(Integer id) {
        this.id = id;
    }

}
