package amazon.layer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import amazon.layer.domainn.Role;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String role);

}