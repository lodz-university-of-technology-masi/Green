package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name = "question_number")
@NamedQuery(name = "QuestionNumber.findAll", query = "SELECT q FROM QuestionNumber q")
public class QuestionNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
}
