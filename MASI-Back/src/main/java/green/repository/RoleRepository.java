package green.repository;

import green.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT r FROM Role r WHERE r.id = :id")
    Role findById(@Param("id") int id);
}