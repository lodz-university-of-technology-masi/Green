package green.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="member")
@NamedQuery(name="Member.findAll", query="SELECT a FROM Member a")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Boolean active;

	private String login;

	private String name;

	@JsonIgnore
	private String password;

	private Role role;

	@OneToMany(mappedBy="member")
	private List<Session> sessions;

	public Session addSession(Session session) {
		getSessions().add(session);
		session.setMember(this);
		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setMember(null);
		return session;
	}

}