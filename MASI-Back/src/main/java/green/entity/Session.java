package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@NoArgsConstructor
@Data
@Entity
@Table(name="session")
@NamedQuery(name="Session.findAll", query="SELECT s FROM Session s")
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="add_date")
	private Timestamp addDate;

	private String token;

	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
}