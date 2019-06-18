package green.repository;

import green.entity.QuestionScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface QuestionScaleRepository extends JpaRepository<QuestionScale, Integer> {
}
