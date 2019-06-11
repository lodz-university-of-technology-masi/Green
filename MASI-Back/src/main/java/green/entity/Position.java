package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name="position")
@NamedQuery(name="Position.findAll", query="SELECT p FROM Position p")
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean active;
}
