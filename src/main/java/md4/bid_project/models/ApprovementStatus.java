package md4.bid_project.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "approvement_statuses")
@Data
public class ApprovementStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approvement_status_id")
    private Long id;

    @Column(name = "approvement_status_name")
    private String name;

}
