package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "question_choose")
@NamedQuery(name = "QuestionChoose.findAll", query = "SELECT q FROM QuestionChoose q")
public class QuestionChoose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @OneToMany(mappedBy = "answer")
    private List<Answer> possibleAnswers;
}
