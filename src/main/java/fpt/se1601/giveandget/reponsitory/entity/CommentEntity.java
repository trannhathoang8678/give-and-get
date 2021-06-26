package fpt.se1601.giveandget.reponsitory.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @Column(name = "USER_id")
    private int userId;
    @Column(name = "DONATION_id")
    private int donationId;
    @Column(name = "content")
    private String content;
    @Column(name = "created_timestamp", updatable = false, insertable = false)
    private Timestamp createdTimestamp;
    @Column(name = "update_timestamp", updatable = false, insertable = false)
    private Timestamp updateTimestamp;

    public CommentEntity(int userId, int donationId, String content) {
        this.userId = userId;
        this.donationId = donationId;
        this.content = content;
    }
}
