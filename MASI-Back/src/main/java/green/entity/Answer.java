package green.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Answer")
@NamedQuery(name = "Answer.findAll", query = "SELECT q FROM Answer q")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer question_id;

    private String answer;
}
