package amazon.layer.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import amazon.layer.domainn.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String username);

	@Query("select u from User u inner join u.roles r where r.name = :role and u.isActive = false")
	Set<User> findBySpecificRoles(@Param("role") String role);

}
