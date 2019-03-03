package com.cs.ajiinya.movieinfoservice.resources;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.ajiinya.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathParam("movieId") String movieId) {
		return new Movie(movieId, "Test Name");
	}

}
