package amazon.layer.controller;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import amazon.layer.domainn.Review;
import amazon.layer.service.ReviewService;


@Controller
@RequestMapping(value = "/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	
	@RequestMapping(value = "/getReviws"  , method =  RequestMethod.GET)
	public String getReviws(Model model)
	{
		Set<Review> reviews = reviewService.getReviews();
		model.addAttribute("reviews",reviews);
		return "adminPindingReview";
	}
	
	
	@RequestMapping(value = "/enableReview" , method = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST} )
	public String setReviewStatus(@RequestParam("id") Long id)
	{
		System.out.println("get controller");
		reviewService.setReviewStatus(id);
		return "redirect:/review/getReviws";
	}

}
