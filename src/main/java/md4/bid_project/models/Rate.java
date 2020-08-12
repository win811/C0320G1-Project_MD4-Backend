package md4.bid_project.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rates")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    @Column(name = "rate_name")
    private String name;

//    @OneToMany(mappedBy = "rates")
//    private Set<Member> members;

    public Rate() {
    }

    public Rate(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
