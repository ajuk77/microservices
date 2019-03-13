package com.cs.ajinkya.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cs.ajinkya.moviecatalogservice.models.CatalogItem;
import com.cs.ajinkya.moviecatalogservice.models.Movie;
import com.cs.ajinkya.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4), 
				new Rating("5678", 3));
		
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Desc", rating.getRating()); 
		}).collect(Collectors.toList());
		
		
		//return Collections.singletonList(new CatalogItem("Transformer", "Test", 4));
	}

}
