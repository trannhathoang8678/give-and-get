package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "`COMMENT`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_id")
    private Donation donation;
    @Column(name = "content")
    private String content;
}