package md4.bid_project.models;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rates")
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    @Column(name = "rate_name")
    private String name;

}
