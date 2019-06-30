package green.entity;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "test")
//@NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")
public class Test {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="redactor")
    private String redactor;

    @Column(name="position")
    private String position;

    @Column(name="candidate")
    private String candidate;

    @OneToMany(mappedBy = "test")
    private List<Version> versions;

    public Test() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRedactor() {
        return redactor;
    }

    public void setRedactor(String redactor) {
        this.redactor = redactor;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
