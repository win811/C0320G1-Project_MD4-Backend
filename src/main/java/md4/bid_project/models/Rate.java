package md4.bid_project.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rates")
@Getter
@Setter
@NoArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    @Column(name = "rate_name")
    private String name;

}
