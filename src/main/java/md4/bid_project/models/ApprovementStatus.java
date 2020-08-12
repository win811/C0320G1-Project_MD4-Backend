package md4.bid_project.models;

import javax.persistence.*;

@Entity
@Table(name = "approvement_statuses")
public class ApprovementStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "approvement_status_name")
    private boolean status;

    public ApprovementStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
