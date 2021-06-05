package fpt.se1601.giveandget.reponsitory.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "`REPORT`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DONATION_id")
    private Donation donation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORT_TYPE_id")
    private ReportType reportType;
    @Column(name = "description")
    private String description;
}
