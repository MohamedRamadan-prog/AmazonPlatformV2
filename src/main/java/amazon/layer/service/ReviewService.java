package amazon.layer.service;

import java.util.Set;
import amazon.layer.domainn.Review;



public interface ReviewService {

	public Set<Review> getReviews();
	public boolean setReviewStatus(Long id);
	public void addReview(String comment, Long productId);
}
