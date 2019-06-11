package green.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@Entity
@Data
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Boolean active;

	private String name;

	@OneToMany(mappedBy="role")
	private List<Member> members;

	public Member addMember(Member member) {
		getMembers().add(member);
		member.setRole(this);
		return member;
	}

	public Member removeMember(Member member) {
		getMembers().remove(member);
		member.setRole(null);
		return member;
	}

}