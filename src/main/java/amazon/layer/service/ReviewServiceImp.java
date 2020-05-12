package amazon.layer.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.layer.domainn.Product;
import amazon.layer.domainn.Review;
import amazon.layer.repository.ProductRepository;
import amazon.layer.repository.ReviewRepository;

@Service
public class ReviewServiceImp implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Set<Review> getReviews() {

		Set<Review> reviews = reviewRepository.getPenddingReviews();

		return reviews;
	}

	@Override
	public boolean setReviewStatus(Long id) {
		Review review = reviewRepository.findById(id).orElse(null);
		if (review == null)
			return false;
		review.setAccepted(true);
		reviewRepository.save(review);
		return true;
	}

	@Override
	public void addReview(String comment, Long productId) {

		Review review = new Review(comment);

		Product product = productRepository.findById(productId).get();

		product.addReviews(review);

		productRepository.save(product);

	}

}
