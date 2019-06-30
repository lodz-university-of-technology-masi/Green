package green.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "version")
//@NamedQuery(name = "Version.findAll", query = "SELECT t FROM Version t")
public class Version {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="language")
    private String language;

    @ManyToOne
    @JoinColumn(name="test_id")
    private Test test;

    @OneToMany(mappedBy = "version")
    private List<Question> questions;


    public Version() {
    }

    public Version(String language, Test test, List<Question> questions) {
        this.language = language;
        this.test = test;
        this.questions = questions;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
