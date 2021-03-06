package com.westerly.movieinfoservice.controllers;

import javax.servlet.http.HttpServletRequest;

import com.westerly.movieinfoservice.models.Movie;
import com.westerly.movieinfoservice.models.MovieSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieInfoController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @RequestMapping("/movie/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        MovieSummary movieSummary = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview(), movieId);

    }
    @GetMapping("/host-info") 
    public String getHostHeader(HttpServletRequest request){
        return request.getHeader("host");
    }
}
