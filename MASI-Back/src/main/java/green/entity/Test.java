package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "id")
    private List<Member> redactors;

    @OneToMany(mappedBy = "id")
    private List<Member> candidates;

    @OneToMany(mappedBy = "id")
    private List<Version> versions;

    @OneToMany(mappedBy = "id")
    private List<Attempt> attempts;
}
