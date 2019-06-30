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

    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.password = :password")
    Member findByLoginAndPassword(@Param("name") String name, @Param("password") String password);

    @Query("SELECT m FROM Member m WHERE m.name = :name")
    Member findByLogin(@Param("name") String name);

//    @Query("SELECT m FROM Member m WHERE m.id = :id")
//    Member findById(@Param("id") int id);
}
