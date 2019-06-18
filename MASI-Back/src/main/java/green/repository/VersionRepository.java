package green.repository;

import green.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface VersionRepository extends JpaRepository<Version, Integer> {
}
