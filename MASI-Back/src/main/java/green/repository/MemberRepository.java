package green.repository;

import green.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT m FROM Member m WHERE m.login = :login AND m.password = :password")
    Member findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("SELECT m FROM Member m WHERE m.login = :login")
    Member findByLogin(@Param("login") String login);

    @Query("SELECT m FROM Member m WHERE m.id = :id")
    Member findById(@Param("id") int id);
}
