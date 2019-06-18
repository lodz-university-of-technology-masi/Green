package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name = "question_open")
@NamedQuery(name = "QuestionOpen.findAll", query = "SELECT q FROM QuestionOpen q")
public class QuestionOpen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
}
