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
    @JoinColumn(name = "USER_id", updatable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_id", updatable = false)
    private DonationEntity donation;
    @Column(name = "description", updatable = false)
    private String description;
    @Column(name = "reply")
    private String reply;
    @Column(name = "created_timestamp", updatable = false, insertable = false)
    private Timestamp createdTimestamp;

    public ReportEntity(UserEntity user, DonationEntity donation, String description) {
        this.user = user;
        this.donation = donation;
        this.description = description;
    }

    public ReportEntity(Integer id, String reply) {
        this.id = id;
        this.reply = reply;
    }
}
