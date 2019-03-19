package com.cs.ajiinya.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.ajiinya.ratingsdataservice.models.Rating;
import com.cs.ajiinya.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("/users/{userId}")
	public UserRating getRatingsForUsers(@PathVariable("userId") String userId) {
		List<Rating> response = Arrays.asList(new Rating("1234", 4), new Rating("5679", 5));

		UserRating rating = new UserRating();
		rating.setUserRating(response);
		return rating;

	}

}
