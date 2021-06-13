package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "`COMMENT`")
@Getter
@Setter
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_id")
    private DonationEntity donationEntity;
    @Column(name = "content")
    private String content;
}
