package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name = "question_scale")
@NamedQuery(name = "QuestionScale.findAll", query = "SELECT q FROM QuestionScale q")
public class QuestionScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private double min;

    private double max;
}
