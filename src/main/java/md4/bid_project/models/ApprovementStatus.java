package md4.bid_project.models;

import javax.persistence.*;

@Entity
@Table(name = "approvement_statuses")
public class ApprovementStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approvement_status_name")
    private String status;

    public ApprovementStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
