package amazon.layer.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import amazon.layer.domainn.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

	@Query("select r from Review r where r.accepted = false")
	public Set<Review> getPenddingReviews();
	
}