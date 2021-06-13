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
public class UserEntity {
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
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "TOKEN_id")
    private TokenEntity tokenEntity;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<RelationshipEntity> relationshipEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<CommentEntity> commentEntities;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<InterestedDonationEntity> interestedDonationEntities;
    public UserEntity(String email, TokenEntity tokenEntity) {
        this.email = email;
        this.tokenEntity = tokenEntity;
    }
}
