package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_id")
    private DonationEntity donation;
    @Column(name = "description")
    private String description;
    @Column(name = "reply")
    private String reply;
    @Column(name = "created_timestamp", updatable = false,insertable = false)
    private Timestamp createdTimestamp;
}
