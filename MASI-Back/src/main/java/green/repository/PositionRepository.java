package green.repository;

import green.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
