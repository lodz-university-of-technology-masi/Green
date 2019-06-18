package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "attempt")
@NamedQuery(name = "Attempt.findAll", query = "SELECT q FROM Attempt q")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member candidate;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "answer")
    private List<Answer> openAnwers;

    @OneToMany(mappedBy = "answer")
    private List<Answer> chooseAnwers;

    @OneToMany(mappedBy = "answer")
    private List<Answer> numberAnwers;

    @OneToMany(mappedBy = "answer")
    private List<Answer> scaleAnwers;
}
