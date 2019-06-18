package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "test")
@NamedQuery(name = "Test.findAll", query = "SELECT t FROM Test t")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer redactor_id;

    //@OneToMany()
    //private Position position;

    //private Member candidate;

    //private List<Version> versions;
}
