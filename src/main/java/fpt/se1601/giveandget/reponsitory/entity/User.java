package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`USER`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "link_contact_info")
    private String linkContactInfo;
    @Column(name = "role")
    private String role;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOKEN_id")
    private Token token;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Relationship> relationships;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Comment> commentss;
}
