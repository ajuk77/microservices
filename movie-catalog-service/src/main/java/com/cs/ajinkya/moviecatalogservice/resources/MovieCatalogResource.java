package com.cs.ajinkya.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cs.ajinkya.moviecatalogservice.models.CatalogItem;
import com.cs.ajinkya.moviecatalogservice.models.Movie;
import com.cs.ajinkya.moviecatalogservice.models.Rating;
import com.cs.ajinkya.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		
		UserRating response = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
		List<Rating> ratings = response.getUserRating();
		
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
			
			return new CatalogItem(movie.getName(), "Desc", rating.getRating()); 
		}).collect(Collectors.toList());
		
	}

}


/*Movie movie = webClientBuilder.build()
.get()
.uri("http://localhost:8082/movies/"+rating.getMovieId())
.retrieve()
.bodyToMono(Movie.class) // passing an empty movie object and async call will populate the object as and when it gets the data
.block(); // blocking the execution till the mono gets full filled.

*/
