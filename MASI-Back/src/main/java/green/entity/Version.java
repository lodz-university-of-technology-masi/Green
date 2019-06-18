package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "version")
@NamedQuery(name = "Version.findAll", query = "SELECT t FROM Version t")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Language language;

    @OneToMany(mappedBy = "id")
    private List<QuestionOpen> openQuestions;

    @OneToMany(mappedBy = "id")
    private List<QuestionScale> scaleQuestions;

    @OneToMany(mappedBy = "id")
    private List<QuestionNumber> numberQuestions;

    @OneToMany(mappedBy = "id")
    private List<QuestionChoose> chooseQuestions;
}
