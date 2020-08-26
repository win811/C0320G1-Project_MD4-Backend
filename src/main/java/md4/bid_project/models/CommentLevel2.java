package md4.bid_project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;

@Entity
@Table(name="comment_level2")
@Getter
@Setter
@NoArgsConstructor
public class CommentLevel2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_level2_id")
    private Long id;

    @Column(name = "comment_level2_content")
    private String content;

    @ManyToOne
    @JoinColumn(name="comment_level1_id")
    @JsonIgnore
    private CommentLevel1 commentLevel1;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
