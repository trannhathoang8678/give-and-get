package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "`REPORT`")
@Getter
@Setter
@NoArgsConstructor
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_id")
    private DonationEntity donationEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORT_TYPE_id")
    private ReportTypeEntity reportTypeEntity;
    @Column(name = "description")
    private String description;
}
