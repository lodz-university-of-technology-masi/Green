package green.repository;

import green.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
}
