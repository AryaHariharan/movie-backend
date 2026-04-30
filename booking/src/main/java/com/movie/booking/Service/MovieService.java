package com.movie.booking.Service;

import com.movie.booking.Entity.Movie;
import com.movie.booking.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repo;

    public Movie addMovie(Movie movie) {
        return repo.save(movie);
    }

    public List<Movie> getAllMovies() {
        return repo.findAll();
    }

    public Movie getMovieById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return repo.findByGenre(genre);
    }

    public void deleteMovie(Long id) {
        repo.deleteById(id);
    }
}