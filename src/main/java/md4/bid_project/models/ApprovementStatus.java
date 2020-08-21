package md4.bid_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "approvement_statuses")
@Getter
@Setter
@NoArgsConstructor
public class ApprovementStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approvement_status_id")
    private Long id;

    @Column(name = "approvement_status_name")
    private String name;

}
