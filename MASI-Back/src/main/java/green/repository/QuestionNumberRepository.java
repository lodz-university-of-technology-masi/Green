package green.repository;

import green.entity.QuestionNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface QuestionNumberRepository extends JpaRepository<QuestionNumber, Integer> {
}
