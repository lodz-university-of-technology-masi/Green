package green.repository;

import green.entity.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SessionRepository extends JpaRepository<Session, Integer> {

	@Query("SELECT s FROM Session s WHERE s.token = :token")
    Session findByToken(@Param("token") String token, Sort sort);
}
